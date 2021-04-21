package com.demo.flightsearch.domain;

import com.demo.flightsearch.domain.common.AuditValuesEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The type Airport.
 */
@Entity
public class Airport extends AuditValuesEntity {

    @Id
    private String airportCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String countryCode;

    /**
     * Gets airport code.
     *
     * @return the airport code
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * Sets airport code.
     *
     * @param airportCode the airport code
     * @return the airport code
     */
    public Airport setAirportCode(String airportCode) {
        this.airportCode = airportCode;
        return this;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public Airport setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     * @return the city
     */
    public Airport setCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * Gets country code.
     *
     * @return the country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets country code.
     *
     * @param countryCode the country code
     * @return the country code
     */
    public Airport setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    @Override
    public String toString() {
        return "Airline{airportCode=" + airportCode
                + ", name=" + name
                + ", city=" + city
                + ", countryCode=" + countryCode
                + "}"+ super.toString();
    }

}
