package com.demo.flightsearch.transformer;

import com.demo.flightsearch.api.airline.AirlineDto;
import com.demo.flightsearch.domain.Airline;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Airline transformer.
 */
@Component
public class AirlineTransformer {

    /**
     * Domain to dto list.
     *
     * @param domains the domains
     * @return the list
     */
    public List<AirlineDto> domainToDto(List<Airline> domains) {
        return (List)domains.stream().filter(Objects::nonNull).map((domain) -> {
            return this.domainToDto(domain);
        }).collect(Collectors.toList());
    }

    /**
     * Domain to dto airline dto.
     *
     * @param domain the domain
     * @return the airline dto
     */
    public AirlineDto domainToDto(Airline domain) {
        return new AirlineDto()
                .setAirlineCode(domain.getAirlineCode())
                .setName(domain.getName())
                .setCountryCode(domain.getCountryCode());
    }

    /**
     * Dto to domain airline.
     *
     * @param domain the domain
     * @param dto    the dto
     * @return the airline
     */
    public Airline dtoToDomain (Airline domain, AirlineDto dto) {
        return domain
                .setAirlineCode(dto.getAirlineCode()!=null?dto.getAirlineCode():domain.getAirlineCode())
                .setName(dto.getName()!=null?dto.getName(): domain.getName())
                .setCountryCode(dto.getCountryCode()!=null?dto.getCountryCode(): domain.getCountryCode());
    }

}
