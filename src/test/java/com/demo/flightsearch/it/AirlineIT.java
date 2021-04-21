package com.demo.flightsearch.it;

import com.demo.flightsearch.DataIT;
import com.demo.flightsearch.api.airline.AirlineDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.demo.flightsearch.TestData.given_AirlineDto;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Airline it.
 */
public class AirlineIT extends DataIT {

    /**
     * Test create new airline.
     */
    @Test
    public void test_create_new_Airline() {
        AirlineDto dto = given_AirlineDto("LU","Lufthansa","DE");

        ResponseEntity<AirlineDto> result = template.withBasicAuth("manager", "password").
                postForEntity("/airlines", dto, AirlineDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getAirlineCode()).isEqualTo("LU");
    }

    /**
     * Test get airline by country code.
     */
    @Test
    public void test_get_Airline_by_country_code() {
        // GIVEN
        AirlineDto AirlineDto1 = createAirline(given_AirlineDto("AI","Air India","IN"));
        AirlineDto AirlineDto2 = createAirline(given_AirlineDto("SJ","Spice Jet","IN"));

        // WHEN
        List<AirlineDto> AirlineDtos = template.withBasicAuth("manager", "password").
                getForEntity("/airlines/countryCode=IN", List.class).getBody();

        // THEN and Verify
        assertThat(AirlineDtos.size()).isEqualTo(2);
    }

    /**
     * Test update airline by airline code.
     */
    @Test
    public void test_update_Airline_by_airline_code() {
        // GIVEN
        AirlineDto AirlineDto1 = createAirline(given_AirlineDto("LU","Lufthansa","DE"));
        AirlineDto toUpdate = AirlineDto1.setName("New Name");
        // WHEN
        template.withBasicAuth("manager", "password").
                put("/airlines/LU",toUpdate);

        // THEN and Verify
        AirlineDto updated = template.withBasicAuth("manager", "password").
                getForEntity("/airlines/LU", AirlineDto.class).getBody();
        assertThat(updated.getName()).isEqualTo(toUpdate.getName());
    }

    /**
     * Test delete airline by airline code.
     */
    @Test
    public void test_delete_Airline_by_airline_code() {
        // GIVEN
        AirlineDto AirlineDto1 = createAirline(given_AirlineDto("LU","Lufthansa","DE"));
        // WHEN
        template.withBasicAuth("manager", "password").
                delete("/Airlines/"+ AirlineDto1.getAirlineCode());

        // THEN and Verify
        AirlineDto response = template.withBasicAuth("manager", "password").
                getForEntity("/Airlines/"+ AirlineDto1.getAirlineCode(), AirlineDto.class).getBody();
        assertThat(response.getAirlineCode()).isEqualTo(null);
    }

}
