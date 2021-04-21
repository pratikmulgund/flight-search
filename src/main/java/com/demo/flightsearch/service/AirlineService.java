package com.demo.flightsearch.service;

import com.demo.flightsearch.api.airline.AirlineDto;
import com.demo.flightsearch.common.exception.NotFoundException;
import com.demo.flightsearch.domain.Airline;
import com.demo.flightsearch.repository.AirlineRepository;
import com.demo.flightsearch.transformer.AirlineTransformer;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The type Airline service.
 */
@Service
public class AirlineService {

    private final AirlineRepository repository;
    private final AirlineTransformer transformer;

    /**
     * Instantiates a new Airline service.
     *
     * @param repository  the repository
     * @param transformer the transformer
     */
    public AirlineService(
            AirlineRepository repository,
            AirlineTransformer transformer
    ) {
        this.repository = repository;
        this.transformer = transformer;
    }

    /**
     * Create airline dto.
     *
     * @param dto the dto
     * @return the airline dto
     */
    public AirlineDto create(AirlineDto dto) {
        return transformer.domainToDto(repository.save(transformer.dtoToDomain(new Airline(), dto)));
    }

    /**
     * Update airline dto.
     *
     * @param AirlineCode the airline code
     * @param dto         the dto
     * @return the airline dto
     */
    public AirlineDto update(String AirlineCode, AirlineDto dto)  {
        Optional<Airline> AirlineOptional = repository.findById(AirlineCode);
        if(AirlineOptional.isEmpty())
            throw new NotFoundException("Airline not found with Airline code:"+AirlineCode);
        Airline saved = repository.save(transformer.dtoToDomain(AirlineOptional.get(),dto));
        return transformer.domainToDto(saved);
    }

    /**
     * Delete.
     *
     * @param AirlineCode the airline code
     */
    public void delete(String AirlineCode){
        if(repository.findById(AirlineCode).isPresent())
            repository.deleteById(AirlineCode);
    }

    /**
     * Find by country code list.
     *
     * @param countryCode the country code
     * @return the list
     */
    public List<AirlineDto> findByCountryCode(String countryCode){
        List<Airline> Airlines = repository.findAirlinesByCountryCode(countryCode);
        if(!Airlines.isEmpty()){
            return transformer.domainToDto(Airlines);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * Find by id airline dto.
     *
     * @param AirlineCode the airline code
     * @return the airline dto
     */
    public AirlineDto findById(String AirlineCode){
        Optional<Airline> Airline = repository.findById(AirlineCode);
        if(Airline.isPresent()){
            return transformer.domainToDto(Airline.get());
        }
        throw new NotFoundException("Airline not found with Airline code:"+AirlineCode);
    }
}
