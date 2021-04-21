package com.demo.flightsearch.repository;

import com.demo.flightsearch.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Airport repository.
 */
@Repository
public interface AirportRepository extends JpaRepository<Airport,String> {

    /**
     * Find airports by country code list.
     *
     * @param countryCode the country code
     * @return the list
     */
    List<Airport> findAirportsByCountryCode(String countryCode);

}
