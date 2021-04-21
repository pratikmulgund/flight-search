package com.demo.flightsearch.api.route;

import com.demo.flightsearch.api.validators.AutoGeneratedValue;
import com.demo.flightsearch.api.validators.ExistingAirlineValidator;
import com.demo.flightsearch.api.validators.ExistingAirportValidator;
import com.demo.flightsearch.api.validators.group.ValidateDuringCreate;
import com.demo.flightsearch.api.validators.group.ValidateDuringUpdate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;


/**
 * The type Route dto.
 */
public class RouteDto {

    @AutoGeneratedValue
    private UUID uuid;

    @Valid
    @ExistingAirlineValidator(groups = {ValidateDuringCreate.class, ValidateDuringUpdate.class})
    private String airlineCode;

    @Valid
    @ExistingAirportValidator(groups = {ValidateDuringCreate.class, ValidateDuringUpdate.class})
    private String departureAirportCode;

    @Valid
    @ExistingAirportValidator(groups = {ValidateDuringCreate.class, ValidateDuringUpdate.class})
    private String arrivalAirportCode;

    @NotNull
    private ZonedDateTime departureDate;

    @NotNull
    private ZonedDateTime arrivalDate;

    @NotNull
    private Double costInEuro;

    /**
     * Gets uuid.
     *
     * @return the uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Sets uuid.
     *
     * @param uuid the uuid
     * @return the uuid
     */
    public RouteDto setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * Gets airline code.
     *
     * @return the airline code
     */
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * Sets airline code.
     *
     * @param airlineCode the airline code
     * @return the airline code
     */
    public RouteDto setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
        return this;
    }

    /**
     * Gets departure airport code.
     *
     * @return the departure airport code
     */
    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    /**
     * Sets departure airport code.
     *
     * @param departureAirportCode the departure airport code
     * @return the departure airport code
     */
    public RouteDto setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
        return this;
    }

    /**
     * Gets arrival airport code.
     *
     * @return the arrival airport code
     */
    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    /**
     * Sets arrival airport code.
     *
     * @param arrivalAirportCode the arrival airport code
     * @return the arrival airport code
     */
    public RouteDto setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
        return this;
    }

    /**
     * Gets departure date.
     *
     * @return the departure date
     */
    public ZonedDateTime getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets departure date.
     *
     * @param departureDate the departure date
     * @return the departure date
     */
    public RouteDto setDepartureDate(ZonedDateTime departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    /**
     * Gets arrival date.
     *
     * @return the arrival date
     */
    public ZonedDateTime getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Sets arrival date.
     *
     * @param arrivalDate the arrival date
     * @return the arrival date
     */
    public RouteDto setArrivalDate(ZonedDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    /**
     * Gets cost in euro.
     *
     * @return the cost in euro
     */
    public Double getCostInEuro() {
        return costInEuro;
    }

    /**
     * Sets cost in euro.
     *
     * @param costInEuro the cost in euro
     * @return the cost in euro
     */
    public RouteDto setCostInEuro(Double costInEuro) {
        this.costInEuro = costInEuro;
        return this;
    }

    @Override
    public String toString() {
        return "routeDto{uuid=" + uuid
                + ", airlineCode=" + airlineCode
                + ", departureAirportCode=" + departureAirportCode
                + ", arrivalAirportCode=" + arrivalAirportCode
                + ", departureDate=" + departureDate
                + ", arrivalDate=" + arrivalDate
                + ", costInEuro=" + costInEuro
                + "}";
    }
}