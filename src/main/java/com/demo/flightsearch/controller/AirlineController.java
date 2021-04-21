package com.demo.flightsearch.controller;

import com.demo.flightsearch.api.airline.AirlineApi;
import com.demo.flightsearch.api.airline.AirlineDto;
import com.demo.flightsearch.service.AirlineService;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The type Airline controller.
 */
@RestController
public class AirlineController extends BaseController implements AirlineApi {

    private final AirlineService service;

    /**
     * Instantiates a new Airline controller.
     *
     * @param service the service
     */
    public AirlineController(
            AirlineService service
    ) {
        this.service = service;
    }

    @Override
    @Transactional
    public AirlineDto create(AirlineDto AirlineDto) {
        return service.create(AirlineDto);
    }

    @Override
    @Transactional
    public AirlineDto update(String AirlineCode, AirlineDto AirlineDto) {
        return service.update(AirlineCode,AirlineDto);
    }

    @Override
    public List<AirlineDto> getByCountryCode(String countryCode) {
        return service.findByCountryCode(countryCode);
    }

    @Override
    public AirlineDto getById(String airpotCode) {
        return service.findById(airpotCode);
    }

    @Override
    public void delete(String AirlineCode) {
        service.delete(AirlineCode);
    }
}

