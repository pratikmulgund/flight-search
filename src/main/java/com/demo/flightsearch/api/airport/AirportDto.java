package com.demo.flightsearch.api.airport;

/**
 * The type Airport dto.
 */
public class AirportDto {

    private String airportCode;

    private String name;

    private String city;

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
    public AirportDto setAirportCode(String airportCode) {
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
    public AirportDto setName(String name) {
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
    public AirportDto setCity(String city) {
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
    public AirportDto setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    @Override
    public String toString() {
        return "AirlineDto{airportCode=" + airportCode
                + ", name=" + name
                + ", city=" + city
                + ", countryCode=" + countryCode
                + "}";
    }
}
