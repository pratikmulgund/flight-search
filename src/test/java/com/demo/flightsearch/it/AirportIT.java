package com.demo.flightsearch.it;

import com.demo.flightsearch.AbstractIT;
import com.demo.flightsearch.DataIT;
import com.demo.flightsearch.api.airport.AirportDto;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.demo.flightsearch.TestData.given_airportDto;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Airport it.
 */
public class AirportIT extends DataIT {

    /**
     * Test create new airport.
     */
    @Test
    public void test_create_new_airport() {
        AirportDto dto = given_airportDto("LON","London International","London","GB");

        ResponseEntity<AirportDto> result = template.withBasicAuth("manager", "password").
                postForEntity("/airports", dto, AirportDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getAirportCode()).isEqualTo("LON");
    }

    /**
     * Test get airport by country code.
     */
    @Test
    public void test_get_airport_by_country_code() {
        // GIVEN
        AirportDto airportDto1 = createAirport(given_airportDto("PAR","Paris International","Paris","FR"));
        AirportDto airportDto2 = createAirport(given_airportDto("NIC","Niece International","Niece","FR"));

        // WHEN
        List<AirportDto> airportDtos = template.withBasicAuth("manager", "password").
        getForEntity("/airports/countryCode=FR", List.class).getBody();

        // THEN and Verify
        assertThat(airportDtos.size()).isEqualTo(2);
    }

    /**
     * Test update airport by airpot code.
     */
    @Test
    public void test_update_airport_by_airpot_code() {
        // GIVEN
        AirportDto airportDto1 = createAirport(given_airportDto("PAR","Paris International","Paris","FR"));
        AirportDto toUpdate = airportDto1.setName("New Name");
        // WHEN
        template.withBasicAuth("manager", "password").
                put("/airports/PAR",toUpdate);

        // THEN and Verify
        AirportDto updated = template.withBasicAuth("manager", "password").
                getForEntity("/airports/PAR", AirportDto.class).getBody();
        assertThat(updated.getName()).isEqualTo(toUpdate.getName());
    }

    /**
     * Test delete airport by airport code.
     */
    @Test
    public void test_delete_airport_by_airport_code() {
        // GIVEN
        AirportDto airportDto1 = createAirport(given_airportDto("ABC","Paris International","Paris","FR"));
        // WHEN
        template.withBasicAuth("manager", "password").
                delete("/airports/"+ airportDto1.getAirportCode());

        // THEN and Verify
        AirportDto response = template.withBasicAuth("manager", "password").
                getForEntity("/airports/"+ airportDto1.getAirportCode(), AirportDto.class).getBody();
        assertThat(response.getAirportCode()).isEqualTo(null);
    }

}
