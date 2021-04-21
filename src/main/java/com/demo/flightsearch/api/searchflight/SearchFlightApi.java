package com.demo.flightsearch.api.searchflight;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZonedDateTime;
import java.util.List;


import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * The interface Search flight api.
 */
@RequestMapping("/search-flights")
public interface SearchFlightApi {

    /**
     * Gets by filter.
     *
     * @param depAirportCode the dep airport code
     * @param arrAirportCode the arr airport code
     * @param departureDate  the departure date
     * @param arrivalDate    the arrival date
     * @param maxConnections the max connections
     * @param maxResults     the max results
     * @return the by filter
     */
    @RequestMapping(
            method = GET,
            path = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<SearchFlightDto> getByFilter(
            @RequestParam("depAirportCode") String depAirportCode,
            @RequestParam("arrAirportCode") String arrAirportCode,
            @RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime departureDate,
            @RequestParam("arrivalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime arrivalDate,
            @RequestParam("maxConnections")  int maxConnections,
            @RequestParam("maxResults") int maxResults
    );
}

