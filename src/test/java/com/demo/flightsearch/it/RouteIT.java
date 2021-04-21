package com.demo.flightsearch.it;

import com.demo.flightsearch.DataIT;
import com.demo.flightsearch.api.airline.AirlineDto;
import com.demo.flightsearch.api.airport.AirportDto;
import com.demo.flightsearch.api.route.RouteDto;
import com.demo.flightsearch.api.searchflight.SearchFlightDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.List;

import static com.demo.flightsearch.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Route it.
 */
public class RouteIT extends DataIT {

    /**
     * Test create new route.
     */
    @Test
    public void test_create_new_route() {
        AirportDto depAirportDto = createAirport(given_airportDto("LON","London International","London","GB"));
        AirportDto arrAirportDto = createAirport(given_airportDto("FRA","Frankfurt International","Frankfurt","DE"));
        AirlineDto AirlineDto = createAirline(given_AirlineDto("AI","Air India","IN"));
        ZonedDateTime depDate = ZonedDateTime.parse("2021-01-01T00:00:00Z");
        ZonedDateTime arrDate = ZonedDateTime.parse("2021-01-02T00:00:00Z");
        Double cost = Double.valueOf(450);

        RouteDto toSave = given_RouteDto(AirlineDto.getAirlineCode(),depAirportDto.getAirportCode(),arrAirportDto.getAirportCode(),
                depDate,arrDate,cost);
        ResponseEntity<RouteDto> result = template.withBasicAuth("manager", "password").
                postForEntity("/routes", toSave, RouteDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    /**
     * Test delete route by uuid.
     */
    @Test
    public void test_delete_route() {
        // GIVEN
        AirportDto airportDtoFra = createAirport(given_airportDto("FRA","Frankfurt International","Frankfurt","DE"));
        AirportDto airportDtoDbx = createAirport(given_airportDto("DBX","Dubai International","Dubai","UA"));
        AirlineDto AirlineDto1 = createAirline(given_AirlineDto("AI","Air India","IN"));
        RouteDto route1FraToDbx = createRoute(given_RouteDto(
                AirlineDto1.getAirlineCode(),
                airportDtoFra.getAirportCode(),airportDtoDbx.getAirportCode(),
                ZonedDateTime.parse("2021-01-01T18:00:00Z"),ZonedDateTime.parse("2021-01-02T15:00:00Z"),Double.valueOf(450)));

        // WHEN
        template.withBasicAuth("manager", "password").
                delete("/routes/"+ route1FraToDbx.getUuid());


        // THEN and Verify
        RouteDto response = template.withBasicAuth("manager", "password").
                getForEntity("/routes/"+route1FraToDbx.getUuid(), RouteDto.class).getBody();

        assertThat(response.getUuid()).isEqualTo(null);
    }

    /**
     * Test update route by uuid.
     */
    @Test
    public void test_update_route_by_uuid() {
        // GIVEN
        AirportDto airportDtoFra = createAirport(given_airportDto("FRA","Frankfurt International","Frankfurt","DE"));
        AirportDto airportDtoDbx = createAirport(given_airportDto("DBX","Dubai International","Dubai","UA"));
        AirlineDto AirlineDto1 = createAirline(given_AirlineDto("AI","Air India","IN"));
        RouteDto route1FraToDbx = createRoute(given_RouteDto(
                AirlineDto1.getAirlineCode(),
                airportDtoFra.getAirportCode(),airportDtoDbx.getAirportCode(),
                ZonedDateTime.parse("2021-01-01T18:00:00Z"),ZonedDateTime.parse("2021-01-02T15:00:00Z"),Double.valueOf(450)));
        RouteDto toUpdate = route1FraToDbx.setCostInEuro(Double.valueOf(1000));

        // WHEN
        template.withBasicAuth("manager", "password").
                put("/routes/"+route1FraToDbx.getUuid(),toUpdate);

        // THEN and Verify
        RouteDto response = template.withBasicAuth("manager", "password").
                getForEntity("/routes/"+route1FraToDbx.getUuid(), RouteDto.class).getBody();
        assertThat(response.getCostInEuro()).isEqualTo(toUpdate.getCostInEuro());
    }

    /**
     * Test search route.
     */
    @Test
    public void test_search_route() throws JsonProcessingException {

        // Given
        AirportDto airportDtoFra = createAirport(given_airportDto("FRA","Frankfurt International","Frankfurt","DE"));
        AirportDto airportDtoDbx = createAirport(given_airportDto("DBX","Dubai International","Dubai","UA"));
        AirportDto airportDtoSyd = createAirport(given_airportDto("SYD","Sydney International","Sydney","AU"));

        AirlineDto AirlineDto1 = createAirline(given_AirlineDto("AI","Air India","IN"));

        RouteDto route1FraToDbx = createRoute(given_RouteDto(
                AirlineDto1.getAirlineCode(),
                airportDtoFra.getAirportCode(),airportDtoDbx.getAirportCode(),
                ZonedDateTime.parse("2021-01-01T18:00:00Z"),ZonedDateTime.parse("2021-01-02T15:00:00Z"),Double.valueOf(450)));

        RouteDto route1FraToSyd = createRoute(given_RouteDto(
                AirlineDto1.getAirlineCode(),
                airportDtoFra.getAirportCode(),airportDtoSyd.getAirportCode(),
                ZonedDateTime.parse("2021-01-01T08:00:00Z"),ZonedDateTime.parse("2021-01-01T18:00:00Z"),Double.valueOf(1500)));

        // when
        List<SearchFlightDto> response = template.withBasicAuth("manager", "password").
                getForEntity("/routes?depAirportCode=FRA&arrAirportCode=SYD&departureDate=2021-01-01T08:00:00Z", List.class).getBody();

        // then
        assertThat(response.size()).isEqualTo(1);
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        RouteDto dto = mapper.convertValue(response.get(0),RouteDto.class);
        assertThat(dto.getDepartureAirportCode()).isEqualTo(route1FraToSyd.getDepartureAirportCode());
        assertThat(dto.getArrivalAirportCode()).isEqualTo(route1FraToSyd.getArrivalAirportCode());
        assertThat(dto.getDepartureDate()).isEqualTo(route1FraToSyd.getDepartureDate());
        assertThat(dto.getArrivalDate()).isEqualTo(route1FraToSyd.getArrivalDate());
        assertThat(dto.getCostInEuro()).isEqualTo(Double.valueOf(1500));
    }
}
