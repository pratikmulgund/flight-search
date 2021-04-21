package com.demo.flightsearch.repository;

import com.demo.flightsearch.domain.Airport;
import com.demo.flightsearch.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * The interface Route repository.
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, UUID> {

    /**
     * Find all by arrival airport code and departure airport code and departure date between list.
     *
     * @param arrivalAirportCode   the arrival airport code
     * @param departureAirportCode the departure airport code
     * @param fromDate             the from date
     * @param toDate               the to date
     * @return the list
     */
    List<Route> findAllByArrivalAirportCodeAndDepartureAirportCodeAndDepartureDateBetween(String arrivalAirportCode, String departureAirportCode, ZonedDateTime fromDate,ZonedDateTime toDate);

    /**
     * Find all the possible routes by departure date between and arrival date between list.
     *
     * @param departureFromDate the departure from date
     * @param departureToDate   the departure to date
     * @param arrivalFromDate   the arrival from date
     * @param arrivalToDate     the arrival to date
     * @return the list
     */
    List<Route> findAllByDepartureDateBetweenAndArrivalDateBetween(ZonedDateTime departureFromDate,ZonedDateTime departureToDate,
                                                                   ZonedDateTime arrivalFromDate,ZonedDateTime arrivalToDate);

}
