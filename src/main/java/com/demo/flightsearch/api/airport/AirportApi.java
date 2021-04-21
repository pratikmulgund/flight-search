package com.demo.flightsearch.api.airport;

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
 * The interface Airport api.
 */
@RequestMapping("/airports")
public interface AirportApi {

    /**
     * Create airport dto.
     *
     * @param airportDto the airport dto
     * @return the airport dto
     */
    @RequestMapping(
            value = ""
            , method = POST
            , produces = APPLICATION_JSON_VALUE
            , consumes = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(CREATED)
    AirportDto create(
            @Validated(ValidateDuringCreate.class) @RequestBody AirportDto airportDto
    );

    /**
     * Update airport dto.
     *
     * @param uuid       the uuid
     * @param airportDto the airport dto
     * @return the airport dto
     */
    @RequestMapping(
            value = "/{airportCode}"
            , method = PUT
            , produces = APPLICATION_JSON_VALUE
            , consumes = APPLICATION_JSON_VALUE
    )
    AirportDto update(
            @PathVariable("airportCode") String uuid,
            @Validated(ValidateDuringUpdate.class) @RequestBody AirportDto airportDto
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
    List<AirportDto> getByCountryCode(
            @PathVariable("countryCode") String countryCode
    );

    /**
     * Gets by id.
     *
     * @param airpotCode the airpot code
     * @return the by id
     */
    @RequestMapping(
            method = GET,
            path = "/{airpotCode}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    AirportDto getById(
            @PathVariable("airpotCode") String airpotCode
    );

    /**
     * Delete.
     *
     * @param airportCode the airport code
     */
    @RequestMapping(
            value = "/{airportCode}"
            , method = DELETE
    )
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable("airportCode") String airportCode);
}
