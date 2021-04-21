package com.demo.flightsearch.domain;

import com.demo.flightsearch.domain.common.AuditValuesEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The type Airline.
 */
@Entity
public class Airline extends AuditValuesEntity {

    @Id
    private String airlineCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String countryCode;

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
    public Airline setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
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
    public Airline setName(String name) {
        this.name = name;
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
    public Airline setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    @Override
    public String toString() {
        return "Airline{airlineCode=" + airlineCode
                + ", name=" + name
                + ", countryCode=" + countryCode
                + "}"+ super.toString();
    }

}
