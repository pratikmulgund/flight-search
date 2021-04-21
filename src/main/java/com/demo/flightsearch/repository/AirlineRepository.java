package com.demo.flightsearch.repository;

import com.demo.flightsearch.domain.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Airline repository.
 */
@Repository
public interface AirlineRepository extends JpaRepository<Airline,String> {

    /**
     * Find airlines by country code list.
     *
     * @param countryCode the country code
     * @return the list
     */
    List<Airline> findAirlinesByCountryCode(String countryCode);

}
