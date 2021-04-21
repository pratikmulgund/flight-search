package com.demo.flightsearch.controller;

import com.demo.flightsearch.api.airport.AirportApi;
import com.demo.flightsearch.api.airport.AirportDto;
import com.demo.flightsearch.service.AirportService;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The type Airport controller.
 */
@RestController
public class AirportController implements AirportApi {

    private final AirportService service;

    /**
     * Instantiates a new Airport controller.
     *
     * @param service the service
     */
    public AirportController(
            AirportService service
    ) {
        this.service = service;
    }

    @Override
    @Transactional
    public AirportDto create(AirportDto airPortDto) {
        return service.create(airPortDto);
    }

    @Override
    @Transactional
    public AirportDto update(String airPortCode, AirportDto airPortDto) {
        return service.update(airPortCode,airPortDto);
    }

    @Override
    public List<AirportDto> getByCountryCode(String countryCode) {
        return service.findByCountryCode(countryCode);
    }

    @Override
    public AirportDto getById(String airpotCode) {
        return service.findById(airpotCode);
    }

    @Override
    public void delete(String airportCode) {
        service.delete(airportCode);
    }
}
