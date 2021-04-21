package com.demo.flightsearch.service;

import com.demo.flightsearch.api.route.RouteDto;
import com.demo.flightsearch.common.exception.NotFoundException;
import com.demo.flightsearch.domain.Route;
import com.demo.flightsearch.repository.RouteRepository;
import com.demo.flightsearch.transformer.RouteTransformer;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The type Route service.
 */
@Service
public class RouteService {

    private final RouteRepository repository;
    private final RouteTransformer transformer;

    /**
     * Instantiates a new Route service.
     *
     * @param repository  the repository
     * @param transformer the transformer
     */
    public RouteService(
            RouteRepository repository,
            RouteTransformer transformer
    ) {
        this.repository = repository;
        this.transformer = transformer;
    }

    /**
     * Create route dto.
     *
     * @param dto the dto
     * @return the route dto
     */
    public RouteDto create(RouteDto dto) {
        return transformer.domainToDto(repository.save(transformer.dtoToDomain(new Route(), dto)));
    }

    /**
     * Update route dto.
     *
     * @param uuid the uuid
     * @param dto  the dto
     * @return the route dto
     */
    public RouteDto update(UUID uuid, RouteDto dto)  {
        Optional<Route> RouteOptional = repository.findById(uuid);
        if(RouteOptional.isEmpty())
            throw new NotFoundException("Route not found with uuid:"+uuid);
        Route saved = repository.save(transformer.dtoToDomain(RouteOptional.get(),dto));
        return transformer.domainToDto(saved);
    }

    /**
     * Delete.
     *
     * @param uuid the uuid
     */
    public void delete(UUID uuid){
        if(repository.findById(uuid).isPresent())
            repository.deleteById(uuid);
    }

    /**
     * Find by filter list.
     *
     * @param depAirport the dep airport
     * @param arrAirport the arr airport
     * @param depTime    the dep time
     * @return the list
     */
    public List<RouteDto> findByFilter(String depAirport, String arrAirport, ZonedDateTime depTime){
        if(depAirport.equalsIgnoreCase(arrAirport))
            throw new IllegalStateException("'depAirport' cannot be same as 'arrAirport'");

        List<Route> Routes = repository.findAllByArrivalAirportCodeAndDepartureAirportCodeAndDepartureDateBetween(
                arrAirport,
                depAirport,
                depTime.truncatedTo(ChronoUnit.DAYS).minusDays(1),
                depTime.truncatedTo(ChronoUnit.DAYS).plusDays(1));
        if(!Routes.isEmpty()){
            return transformer.domainToDto(Routes);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * Find by id route dto.
     *
     * @param uuid the uuid
     * @return the route dto
     */
    public RouteDto findById(UUID uuid){
        Optional<Route> Route = repository.findById(uuid);
        if(Route.isPresent()){
            return transformer.domainToDto(Route.get());
        }
        throw new NotFoundException("Route not found with uuid:"+uuid);
    }
}
