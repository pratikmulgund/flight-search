package com.demo.flightsearch.controller;

import com.demo.flightsearch.api.searchflight.SearchFlightApi;
import com.demo.flightsearch.api.searchflight.SearchFlightDto;
import com.demo.flightsearch.service.SearchFlightService;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * The type Search flight controller.
 */
@RestController
public class SearchFlightController extends BaseController implements SearchFlightApi {

    private final SearchFlightService service;

    /**
     * Instantiates a new Search flight controller.
     *
     * @param service the service
     */
    public SearchFlightController(
            SearchFlightService service
    ) {
        this.service = service;
    }

    @Override
    public List<SearchFlightDto> getByFilter(String depAirportCode, String arrAirportCode, ZonedDateTime departureDate, ZonedDateTime arrivalDate, int maxConnections, int maxResults) {
        return service.searchFlight(depAirportCode,arrAirportCode,
                departureDate,arrivalDate,maxConnections,maxResults);
    }
}



