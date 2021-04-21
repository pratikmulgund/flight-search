package com.demo.flightsearch.service;

import com.demo.flightsearch.api.airport.AirportDto;
import com.demo.flightsearch.common.exception.NotFoundException;
import com.demo.flightsearch.domain.Airport;
import com.demo.flightsearch.repository.AirportRepository;
import com.demo.flightsearch.transformer.AirportTransformer;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The type Airport service.
 */
@Service
public class AirportService {

    private final AirportRepository repository;
    private final AirportTransformer transformer;

    /**
     * Instantiates a new Airport service.
     *
     * @param repository  the repository
     * @param transformer the transformer
     */
    public AirportService(
            AirportRepository repository,
            AirportTransformer transformer
    ) {
        this.repository = repository;
        this.transformer = transformer;
    }

    /**
     * Create airport dto.
     *
     * @param dto the dto
     * @return the airport dto
     */
    public AirportDto create(@Valid AirportDto dto) {
        return transformer.domainToDto(repository.save(transformer.dtoToDomain(new Airport(), dto)));
    }

    /**
     * Update airport dto.
     *
     * @param airportCode the airport code
     * @param dto         the dto
     * @return the airport dto
     */
    public AirportDto update(String airportCode, AirportDto dto)  {
        Optional<Airport> airportOptional = repository.findById(airportCode);
        if(airportOptional.isEmpty())
            throw new NotFoundException("Airport not found with airport code:"+airportCode);
        Airport saved = repository.save(transformer.dtoToDomain(airportOptional.get(),dto));
        return transformer.domainToDto(saved);
    }

    /**
     * Delete.
     *
     * @param airportCode the airport code
     */
    public void delete(String airportCode){
        if(repository.findById(airportCode).isPresent())
            repository.deleteById(airportCode);
    }

    /**
     * Find by country code list.
     *
     * @param countryCode the country code
     * @return the list
     */
    public List<AirportDto> findByCountryCode(String countryCode){
        List<Airport> airports = repository.findAirportsByCountryCode(countryCode);
        if(!airports.isEmpty()){
            return transformer.domainToDto(airports);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * Find by id airport dto.
     *
     * @param airportCode the airport code
     * @return the airport dto
     */
    public AirportDto findById(String airportCode){
        Optional<Airport> airport = repository.findById(airportCode);
        if(airport.isPresent()){
            return transformer.domainToDto(airport.get());
        }
        throw new NotFoundException("Airport not found with airport code:"+airportCode);
    }
}
