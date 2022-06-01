package org.cresta.weather.controller;

import com.google.maps.errors.ApiException;

import org.cresta.weather.domain.WeatherRequest;

import org.cresta.weather.service.WeatherService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.IOException;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @Test
    public void contextLoads(){
        assertThat(weatherController).isNotNull();
    }

    @Test
    public void givenNoUserSuppliedWhenGettingElapsedTimeShouldReturnErrorResponse() throws IOException, InterruptedException, ApiException {
        WeatherRequest weatherRequest = WeatherRequest.builder().build();
        assertThat(weatherController.getElapsedTime(weatherRequest).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
