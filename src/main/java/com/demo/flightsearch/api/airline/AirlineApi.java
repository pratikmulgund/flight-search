package com.demo.flightsearch.api.airline;

import com.demo.flightsearch.api.validators.group.ValidateDuringCreate;
import com.demo.flightsearch.api.validators.group.ValidateDuringUpdate;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * The interface Airline api.
 */
@RequestMapping("/airlines")
public interface AirlineApi {

    /**
     * Create airline dto.
     *
     * @param airlineDto the airline dto
     * @return the airline dto
     */
    @RequestMapping(
            value = ""
            , method = POST
            , produces = APPLICATION_JSON_VALUE
            , consumes = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(CREATED)
    AirlineDto create(
            @Validated(ValidateDuringCreate.class) @RequestBody AirlineDto airlineDto
    );

    /**
     * Update airline dto.
     *
     * @param uuid       the uuid
     * @param airlineDto the airline dto
     * @return the airline dto
     */
    @RequestMapping(
            value = "/{airlineCode}"
            , method = PUT
            , produces = APPLICATION_JSON_VALUE
            , consumes = APPLICATION_JSON_VALUE
    )
    AirlineDto update(
            @PathVariable("airlineCode") String uuid,
            @Validated(ValidateDuringUpdate.class) @RequestBody AirlineDto airlineDto
    );

    /**
     * Gets by country code.
     *
     * @param countryCode the country code
     * @return the by country code
     */
    @RequestMapping(
            method = GET,
            path = "/countryCode={countryCode}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<AirlineDto> getByCountryCode(
            @PathVariable("countryCode") String countryCode
    );

    /**
     * Gets by id.
     *
     * @param airlineCode the airline code
     * @return the by id
     */
    @RequestMapping(
            method = GET,
            path = "/{airlineCode}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    AirlineDto getById(
            @PathVariable("airlineCode") String airlineCode
    );

    /**
     * Delete.
     *
     * @param airlineCode the airline code
     */
    @RequestMapping(
            value = "/{airlineCode}"
            , method = DELETE
    )
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable("airlineCode") String airlineCode);
}

