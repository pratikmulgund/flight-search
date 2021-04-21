package com.demo.flightsearch;

import com.demo.flightsearch.domain.Airport;
import com.demo.flightsearch.repository.AirportRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Spring data configuration.
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = {AirportRepository.class})
@EntityScan(basePackageClasses = {Airport.class})
@EnableJpaAuditing
public class SpringDataConfiguration {
}
