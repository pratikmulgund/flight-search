package com.demo.flightsearch.api.airline;

/**
 * The type Airline dto.
 */
public class AirlineDto {

    private String airlineCode;

    private String name;

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
    public AirlineDto setAirlineCode(String airlineCode) {
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
    public AirlineDto setName(String name) {
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
    public AirlineDto setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    @Override
    public String toString() {
        return "AirlineDto{airlineCode=" + airlineCode
                + ", name=" + name
                + ", countryCode=" + countryCode
                + "}";
    }

}
