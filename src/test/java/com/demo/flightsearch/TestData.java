package com.demo.flightsearch;

import com.demo.flightsearch.api.airline.AirlineDto;
import com.demo.flightsearch.api.airport.AirportDto;
import com.demo.flightsearch.api.route.RouteDto;

import java.time.ZonedDateTime;

/**
 * The type Test data. This is used to create test data in individual ITs.
 */
public class TestData {

    /**
     * Given airport dto airport dto.
     *
     * @param airPortCode the air port code
     * @param name        the name
     * @param city        the city
     * @param countryCode the country code
     * @return the airport dto
     */
    public static AirportDto given_airportDto (String airPortCode, String name, String city, String countryCode){
        return new AirportDto().setAirportCode(airPortCode)
                .setName(name).setCity(city).setCountryCode(countryCode);
    }

    /**
     * Given airline dto airline dto.
     *
     * @param AirlineCode the airline code
     * @param name        the name
     * @param countryCode the country code
     * @return the airline dto
     */
    public static AirlineDto given_AirlineDto (String AirlineCode, String name, String countryCode){
        return new AirlineDto().setAirlineCode(AirlineCode)
                .setName(name).setCountryCode(countryCode);
    }

    /**
     * Given route dto route dto.
     *
     * @param airlineCode    the airline code
     * @param depAirportCode the dep airport code
     * @param arrAirportCode the arr airport code
     * @param depTime        the dep time
     * @param arrTime        the arr time
     * @param cost           the cost
     * @return the route dto
     */
    public static RouteDto given_RouteDto (String airlineCode,String depAirportCode, String arrAirportCode, ZonedDateTime depTime, ZonedDateTime arrTime,Double cost){
        return new RouteDto()
                .setAirlineCode(airlineCode)
                .setDepartureAirportCode(depAirportCode)
                .setArrivalAirportCode(arrAirportCode)
                .setDepartureDate(depTime)
                .setArrivalDate(arrTime)
                .setCostInEuro(cost);
    }

}
