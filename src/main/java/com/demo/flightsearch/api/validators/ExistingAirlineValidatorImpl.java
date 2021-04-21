package com.demo.flightsearch.api.validators;

import com.demo.flightsearch.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Existing airline validator. Implements the business logic of validation
 */
@Component
public class ExistingAirlineValidatorImpl implements ExistingAirlineValidator.Validator {

    @Autowired
    private AirlineRepository repository;

    @Override
    public boolean isValidAirline(String airlineCode) {
        return airlineCode != null && repository.existsById(airlineCode);
    }
}