package com.demo.flightsearch.service;

import com.demo.flightsearch.api.searchflight.SearchFlightDto;
import com.demo.flightsearch.api.route.RouteDto;
import com.demo.flightsearch.repository.RouteRepository;
import com.demo.flightsearch.transformer.RouteTransformer;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The type Search flight service.
 */
@Service
public class SearchFlightService {

    private final RouteRepository repository;
    private final RouteTransformer transformer;

    /**
     * Instantiates a new Search flight service.
     *
     * @param repository  the repository
     * @param transformer the transformer
     */
    public SearchFlightService(
            RouteRepository repository,
            RouteTransformer transformer
    ) {
        this.repository = repository;
        this.transformer = transformer;
    }

    /**
     * Search flight list.
     *
     * @param depAirportCode the dep airport code
     * @param arrAirportCode the arr airport code
     * @param departureDate  the departure date
     * @param arrivalDate    the arrival date
     * @param maxConnections the max connections
     * @param maxResults     the max results
     * @return the list
     */
    public List<SearchFlightDto> searchFlight(String depAirportCode, String arrAirportCode,
                                              ZonedDateTime departureDate, ZonedDateTime arrivalDate,
                                              int maxConnections, int maxResults) {
        List<SearchFlightDto> searchFlightDtos = new ArrayList<>();

        if (arrivalDate.isBefore(departureDate))
            throw new IllegalStateException("'arrivalDate' cannot be before 'departureDate'");

        //Fetch all possible routes for the given timeframe
        List<RouteDto> allPossibleRoutes = transformer.domainToDto(repository.findAllByDepartureDateBetweenAndArrivalDateBetween(
                departureDate.truncatedTo(ChronoUnit.DAYS).minusDays(1),
                departureDate.truncatedTo(ChronoUnit.DAYS).plusDays(2),
                arrivalDate.truncatedTo(ChronoUnit.DAYS).minusDays(1),
                arrivalDate.truncatedTo(ChronoUnit.DAYS).plusDays(2)));

        //Create a graph
        AllDirectedPaths<String, RouteDto> allRouteGraph = createGraph(allPossibleRoutes);

        //get all the paths of graph for our source and destination airports
        try {
            List<GraphPath<String, RouteDto>> edgeList = allRouteGraph.getAllPaths(depAirportCode, arrAirportCode, true, maxConnections);

            //exclude those routes where arrival time of first connection is greater than departure time of second connection
            List<List<RouteDto>> routeLists = edgeList.stream()
                    .map(i -> i.getEdgeList())
                    .filter(i -> connectingFlightDateTimeCheck(i) == true)
                    .map(i -> getTotalCostMap(i)).sorted(Map.Entry.comparingByKey())
                    .limit(maxResults).map(i -> i.getValue()).collect(Collectors.toList());

            //Order based on lowest total cost and return the response.
            routeLists.stream().forEach(route -> {
                searchFlightDtos.add(
                        new SearchFlightDto()
                                .setUuid(UUID.randomUUID())
                                .setItinerary(route)
                                .setTotalCostInEuro(route.stream().mapToDouble(i -> i.getCostInEuro()).sum())
                );
            });
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("No flights available for this route");
        }

        return searchFlightDtos;
    }

    private AllDirectedPaths<String, RouteDto> createGraph(List<RouteDto> routeDtos) {
        SimpleDirectedWeightedGraph<String, RouteDto> graph = new SimpleDirectedWeightedGraph<String, RouteDto>(
                RouteDto.class);

        routeDtos.stream().forEach(route -> {
            graph.addVertex(route.getDepartureAirportCode());
            graph.addVertex(route.getArrivalAirportCode());
            graph.addEdge(route.getDepartureAirportCode(), route.getArrivalAirportCode(), route);
        });

        return new AllDirectedPaths<>(graph);
    }

    /**
     * Connecting flight date time check boolean.
     *
     * @param routeDtos the route dtos
     * @return the boolean
     */
    public static boolean connectingFlightDateTimeCheck(List<RouteDto> routeDtos) {
        boolean valid = true;
        if (routeDtos.size() > 1) {
            for (int i = 0; i < routeDtos.size() - 1; i++) {
                if (routeDtos.get(i).getArrivalDate().isAfter(routeDtos.get(i + 1).getDepartureDate())) {
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }

    /**
     * Gets total cost map.
     *
     * @param routeDtos the route dtos
     * @return the total cost map
     */
    public static Map.Entry<Double, List<RouteDto>> getTotalCostMap(List<RouteDto> routeDtos) {
        Double cost = Double.valueOf(0);
        for (RouteDto routeDto : routeDtos) {
            cost = cost + routeDto.getCostInEuro();
        }
        return Map.entry(cost, routeDtos);
    }

}
