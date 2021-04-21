package com.demo.flightsearch.transformer;

import com.demo.flightsearch.api.route.RouteDto;
import com.demo.flightsearch.domain.Route;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Route transformer.
 */
@Component
public class RouteTransformer {

    /**
     * Domain to dto list.
     *
     * @param domains the domains
     * @return the list
     */
    public List<RouteDto> domainToDto(List<Route> domains) {
        return (List)domains.stream().filter(Objects::nonNull).map((domain) -> {
            return this.domainToDto(domain);
        }).collect(Collectors.toList());
    }

    /**
     * Domain to dto route dto.
     *
     * @param domain the domain
     * @return the route dto
     */
    public RouteDto domainToDto(Route domain) {
        return new RouteDto()
                .setUuid(domain.getUuid())
                .setAirlineCode(domain.getAirlineCode())
                .setDepartureAirportCode(domain.getDepartureAirportCode())
                .setArrivalAirportCode(domain.getArrivalAirportCode())
                .setDepartureDate(domain.getDepartureDate())
                .setArrivalDate(domain.getArrivalDate())
                .setCostInEuro(domain.getCostInEuro()
        );
    }

    /**
     * Dto to domain route.
     *
     * @param domain the domain
     * @param dto    the dto
     * @return the route
     */
    public Route dtoToDomain (Route domain, RouteDto dto) {
        return domain
                .setAirlineCode(dto.getAirlineCode()!=null?dto.getAirlineCode():domain.getAirlineCode())
                .setDepartureAirportCode(dto.getDepartureAirportCode()!=null?dto.getDepartureAirportCode(): domain.getDepartureAirportCode())
                .setArrivalAirportCode(dto.getArrivalAirportCode()!=null?dto.getArrivalAirportCode(): domain.getArrivalAirportCode())
                .setDepartureDate(dto.getDepartureDate()!=null?dto.getDepartureDate(): domain.getDepartureDate())
                .setArrivalDate(dto.getArrivalDate()!=null?dto.getArrivalDate(): domain.getArrivalDate())
                .setCostInEuro(dto.getCostInEuro()!=null?dto.getCostInEuro(): domain.getCostInEuro());
    }
}
