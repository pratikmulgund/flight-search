package com.demo.flightsearch.transformer;

import com.demo.flightsearch.api.airport.AirportDto;
import com.demo.flightsearch.domain.Airport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Airport transformer.
 */
@Component
public class AirportTransformer {

    /**
     * Domain to dto list.
     *
     * @param domains the domains
     * @return the list
     */
    public List<AirportDto> domainToDto(List<Airport> domains) {
        return (List)domains.stream().filter(Objects::nonNull).map((domain) -> {
            return this.domainToDto(domain);
        }).collect(Collectors.toList());
    }

    /**
     * Domain to dto airport dto.
     *
     * @param domain the domain
     * @return the airport dto
     */
    public AirportDto domainToDto(Airport domain) {
        return new AirportDto()
                .setAirportCode(domain.getAirportCode())
                .setName(domain.getName())
                .setCity(domain.getCity())
                .setCountryCode(domain.getCountryCode());
    }

    /**
     * Dto to domain airport.
     *
     * @param domain the domain
     * @param dto    the dto
     * @return the airport
     */
    public Airport dtoToDomain (Airport domain, AirportDto dto) {
        return domain
                .setAirportCode(dto.getAirportCode()!=null?dto.getAirportCode():domain.getAirportCode())
                .setName(dto.getName()!=null?dto.getName(): domain.getName())
                .setCity(dto.getCity()!=null?dto.getCity(): domain.getCity())
                .setCountryCode(dto.getCountryCode()!=null?dto.getCountryCode(): domain.getCountryCode());
    }

}
