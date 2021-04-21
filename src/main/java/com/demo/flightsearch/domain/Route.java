package com.demo.flightsearch.domain;

import com.demo.flightsearch.domain.common.AuditValuesEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * The type Route.
 */
@Entity
public class Route extends AuditValuesEntity {

    @Id
    private UUID uuid = UUID.randomUUID();

    @Column(nullable = false)
    private String airlineCode;

    @Column(nullable = false)
    private String departureAirportCode;

    @Column(nullable = false)
    private String arrivalAirportCode;

    @Column(nullable = false)
    private ZonedDateTime departureDate;

    @Column(nullable = false)
    private ZonedDateTime arrivalDate;

    @Column(nullable = false)
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
    public Route setUuid(UUID uuid) {
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
    public Route setAirlineCode(String airlineCode) {
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
    public Route setDepartureAirportCode(String departureAirportCode) {
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
    public Route setArrivalAirportCode(String arrivalAirportCode) {
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
     * @param departureTime the departure time
     * @return the departure date
     */
    public Route setDepartureDate(ZonedDateTime departureTime) {
        this.departureDate = departureTime;
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
     * @param arrivalTime the arrival time
     * @return the arrival date
     */
    public Route setArrivalDate(ZonedDateTime arrivalTime) {
        this.arrivalDate = arrivalTime;
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
    public Route setCostInEuro(Double costInEuro) {
        this.costInEuro = costInEuro;
        return this;
    }

    @Override
    public String toString() {
        return "Route{uuid=" + uuid
                + ", airlineCode=" + airlineCode
                + ", departureAirportCode=" + departureAirportCode
                + ", arrivalAirportCode=" + arrivalAirportCode
                + ", departureTime=" + departureDate
                + ", arrivalTime=" + arrivalDate
                + ", costInEuro=" + costInEuro

                + "}"+ super.toString();
    }

}
