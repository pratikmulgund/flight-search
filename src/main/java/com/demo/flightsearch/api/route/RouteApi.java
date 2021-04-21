package com.demo.flightsearch.api.route;

import com.demo.flightsearch.api.validators.group.ValidateDuringCreate;
import com.demo.flightsearch.api.validators.group.ValidateDuringUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * The interface Route api.
 */
@RequestMapping("/routes")
public interface RouteApi {


    /**
     * Create route.
     *
     * @param routeDto the route dto
     * @return the route dto
     */
    @RequestMapping(
            value = ""
            , method = POST
            , produces = APPLICATION_JSON_VALUE
            , consumes = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(CREATED)
    RouteDto create(
            @Validated(ValidateDuringCreate.class) @RequestBody RouteDto routeDto
    );

    /**
     * Update route.
     *
     * @param routeDto the route dto
     * @return the route dto
     */
    @RequestMapping(
            value = "/{uuid}"
            , method = PUT
            , produces = APPLICATION_JSON_VALUE
            , consumes = APPLICATION_JSON_VALUE
    )
    RouteDto update(
            @PathVariable("uuid") UUID uuid,
            @Validated(ValidateDuringUpdate.class) @RequestBody RouteDto routeDto
    );

    /**
     * Gets by filter.
     *
     * @param depAirportCode the dep airport code
     * @param arrAirportCode the arr airport code
     * @param departureDate  the departure date
     * @return the RouteDto matching the filter criteria
     */
    @RequestMapping(
            method = GET,
            path = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<RouteDto> getByFilter(
            @RequestParam("depAirportCode") String depAirportCode,
            @RequestParam("arrAirportCode") String arrAirportCode,
            @RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime departureDate
    );

    /**
     * Gets by id.
     *
     * @param uuid the uuid
     * @return the RouteDto by id
     */
    @RequestMapping(
            method = GET,
            path = "/{uuid}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    RouteDto getById(
            @PathVariable("uuid") UUID uuid
    );

    /**
     * Delete.
     *
     * @param uuid the uuid
     */
    @RequestMapping(
            value = "/{uuid}"
            , method = DELETE
    )
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable("uuid") UUID uuid);

}

