package com.demo.flightsearch;

import com.demo.flightsearch.api.airline.AirlineDto;
import com.demo.flightsearch.api.airport.AirportDto;
import com.demo.flightsearch.api.route.RouteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * The type Data it.
 */
public abstract class DataIT extends AbstractIT{

    /**
     * The Template.
     */
    @Autowired
    protected TestRestTemplate template;

    /**
     * Create airport airport dto.
     *
     * @param dto the dto
     * @return the airport dto
     */
    protected AirportDto createAirport (AirportDto dto){
        return template.withBasicAuth("manager", "password").
                postForEntity("/airports", dto, AirportDto.class).getBody();
    }

    /**
     * Create airline airline dto.
     *
     * @param dto the dto
     * @return the airline dto
     */
    protected AirlineDto createAirline (AirlineDto dto){
        return template.withBasicAuth("manager", "password").
                postForEntity("/airlines", dto, AirlineDto.class).getBody();
    }

    /**
     * Create route route dto.
     *
     * @param dto the dto
     * @return the route dto
     */
    protected RouteDto createRoute(RouteDto dto){
        return template.withBasicAuth("manager", "password").
                postForEntity("/routes", dto, RouteDto.class).getBody();
    }

}