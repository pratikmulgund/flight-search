package com.demo.flightsearch.it;

import com.demo.flightsearch.DataIT;
import com.demo.flightsearch.api.airline.AirlineDto;
import com.demo.flightsearch.api.airport.AirportDto;
import com.demo.flightsearch.api.searchflight.SearchFlightDto;
import com.demo.flightsearch.api.route.RouteDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static com.demo.flightsearch.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Search flight it.
 */
public class SearchFlightIT extends DataIT {

    /**
     * Test create new route.
     */
    @Test
    public void test_search_flight() throws JsonProcessingException {

        // Given
        AirportDto airportDtoHtr = createAirport(given_airportDto("HTR","Heathrow International","London","GB"));
        AirportDto airportDtoFra = createAirport(given_airportDto("FRA","Frankfurt International","Frankfurt","DE"));
        AirportDto airportDtoEdn = createAirport(given_airportDto("EDN","Edinburgh International","Edinburgh","GB"));
        AirportDto airportDtoDbx = createAirport(given_airportDto("DBX","Dubai International","Dubai","UA"));
        AirportDto airportDtoSyd = createAirport(given_airportDto("SYD","Sydney International","Sydney","AU"));
        AirportDto airportDtoKlr = createAirport(given_airportDto("KLR","KualaLumpur International","KualaLumpur","MY"));
        AirportDto airportDtoAuk = createAirport(given_airportDto("AUK","Aukland International","Aukland","NL"));
        AirportDto airportDtoRio = createAirport(given_airportDto("RIO","RioDeJanerio International","RioDeJanerio","BR"));
        AirportDto airportDtoNy = createAirport(given_airportDto( "NY","New York International","New york","US"));
        AirportDto airportDtoDel = createAirport(given_airportDto("DEL","Delhi International","Delhi","IN"));


        AirlineDto AirlineDto1 = createAirline(given_AirlineDto("AI","Air India","IN"));
        AirlineDto AirlineDto2 = createAirline(given_AirlineDto("LU","Lufthansa","DE"));
        AirlineDto AirlineDto3 = createAirline(given_AirlineDto("EM","Emirates","UA"));
        AirlineDto AirlineDto4 = createAirline(given_AirlineDto("BA","British Airways","GB"));

        RouteDto route1FraToDbx = createRoute(given_RouteDto(
                AirlineDto2.getAirlineCode(),
                airportDtoFra.getAirportCode(),airportDtoDbx.getAirportCode(),
                ZonedDateTime.parse("2021-01-01T18:00:00Z"),ZonedDateTime.parse("2021-01-02T15:00:00Z"),Double.valueOf(450)));

        RouteDto route1FraToSyd = createRoute(given_RouteDto(
                AirlineDto3.getAirlineCode(),
                airportDtoFra.getAirportCode(),airportDtoSyd.getAirportCode(),
                ZonedDateTime.parse("2021-01-01T08:00:00Z"),ZonedDateTime.parse("2021-01-01T18:00:00Z"),Double.valueOf(1500)));

        RouteDto route1DbxToDel = createRoute(given_RouteDto(
                AirlineDto1.getAirlineCode(),
                airportDtoDbx.getAirportCode(),airportDtoDel.getAirportCode(),
                ZonedDateTime.parse("2021-01-01T20:00:00Z"),ZonedDateTime.parse("2021-01-02T04:00:00Z"),Double.valueOf(300)));

        RouteDto route1FraToDel = createRoute(given_RouteDto(
                AirlineDto1.getAirlineCode(),
                airportDtoFra.getAirportCode(),airportDtoDel.getAirportCode(),
                ZonedDateTime.parse("2021-01-01T04:00:00Z"),ZonedDateTime.parse("2021-01-01T23:00:00Z"),Double.valueOf(550)));

        RouteDto route1DelToSyd = createRoute(given_RouteDto(
                AirlineDto1.getAirlineCode(),
                airportDtoDel.getAirportCode(),airportDtoSyd.getAirportCode(),
                ZonedDateTime.parse("2021-01-02T05:00:00Z"),ZonedDateTime.parse("2021-01-02T15:00:00Z"),Double.valueOf(600)));

        RouteDto route1RioToNy = createRoute(given_RouteDto(
                AirlineDto3.getAirlineCode(),
                airportDtoRio.getAirportCode(),airportDtoNy.getAirportCode(),
                ZonedDateTime.parse("2021-01-01T06:00:00Z"),ZonedDateTime.parse("2021-01-02T13:45:00Z"),Double.valueOf(1500)));

        // when
        List<SearchFlightDto> response = template.withBasicAuth("manager", "password").
                getForEntity("/search-flights?depAirportCode=FRA&arrAirportCode=SYD&departureDate=2021-01-01T08:00:00Z&arrivalDate=2021-01-02T23:00:00Z&maxConnections=3&maxResults=1", List.class).getBody();

        // then
        assertThat(response.size()).isEqualTo(1);
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        SearchFlightDto dto = mapper.convertValue(response.get(0),SearchFlightDto.class);
        assertThat(dto.getItinerary().get(0).getDepartureAirportCode()).isEqualTo(route1FraToDel.getDepartureAirportCode());
        assertThat(dto.getItinerary().get(0).getArrivalAirportCode()).isEqualTo(route1FraToDel.getArrivalAirportCode());
        assertThat(dto.getItinerary().get(1).getDepartureAirportCode()).isEqualTo(route1DelToSyd.getDepartureAirportCode());
        assertThat(dto.getItinerary().get(1).getArrivalAirportCode()).isEqualTo(route1DelToSyd.getArrivalAirportCode());
        assertThat(dto.getTotalCostInEuro()).isEqualTo(Double.valueOf(1150));
    }
}
