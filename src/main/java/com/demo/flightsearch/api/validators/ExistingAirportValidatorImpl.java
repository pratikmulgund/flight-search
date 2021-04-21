package com.demo.flightsearch.api.validators;

import com.demo.flightsearch.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Existing airport validator. Implements the business logic of validation
 */
@Component
public class ExistingAirportValidatorImpl implements ExistingAirportValidator.Validator {

    @Autowired
    private AirportRepository repository;

    @Override
    public boolean isValidAirport(String airlineCode) {
        return airlineCode != null && repository.existsById(airlineCode);
    }
}
