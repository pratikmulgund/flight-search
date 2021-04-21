package com.demo.flightsearch.controller;

import com.demo.flightsearch.api.route.RouteApi;
import com.demo.flightsearch.api.route.RouteDto;
import com.demo.flightsearch.service.RouteService;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * The type Route controller.
 */
@RestController
public class RouteController extends BaseController implements RouteApi {

    private final RouteService service;

    /**
     * Instantiates a new Route controller.
     *
     * @param service the service
     */
    public RouteController(
            RouteService service
    ) {
        this.service = service;
    }

    @Override
    @Transactional
    public RouteDto create(@Valid RouteDto routeDto) {
        return service.create(routeDto);
    }

    @Override
    public RouteDto update(UUID uuid, RouteDto routeDto) {
        return service.update(uuid,routeDto);
    }

    @Override
    public List<RouteDto> getByFilter(String depAirportCode, String arrAirportCode, ZonedDateTime departureDate) {
        return service.findByFilter(depAirportCode,arrAirportCode,departureDate);
    }

    @Override
    public RouteDto getById(UUID uuid) {
        return service.findById(uuid);
    }

    @Override
    public void delete(UUID uuid) {
       service.delete(uuid);
    }
}
