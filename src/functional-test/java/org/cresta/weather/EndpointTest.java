package org.cresta.weather;

import org.cresta.weather.domain.WeatherRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = App.class)
public class EndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnWeatherData() {
        WeatherRequest weatherRequest = WeatherRequest.builder()
                .user("John Doe")
                .location("Denver")
                .build();
        ResponseEntity<String> response = restTemplate.postForEntity("/weather/hello", weatherRequest, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnElapsedTime() {
        WeatherRequest weatherRequest = WeatherRequest.builder()
                .user("John Doe")
                .location("Denver")
                .build();
        restTemplate.postForEntity("/weather/hello", weatherRequest, String.class);
        ResponseEntity<String> response = restTemplate.postForEntity("/weather/goodbye", weatherRequest, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnBadRequestForNoUser() {
        WeatherRequest weatherRequest = WeatherRequest.builder()
                .location("Denver")
                .build();
        ResponseEntity<String> response = restTemplate.postForEntity("/weather/hello", weatherRequest, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestForNoLocation() {
        WeatherRequest weatherRequest = WeatherRequest.builder()
                .user("John Doe")
                .build();
        ResponseEntity<String> response = restTemplate.postForEntity("/weather/hello", weatherRequest, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
