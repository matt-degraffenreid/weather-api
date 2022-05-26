package org.cresta.weather.service;

import com.google.maps.errors.ApiException;
import com.google.maps.errors.NotFoundException;
import com.google.maps.model.LatLng;
import org.cresta.weather.domain.Weather;
import org.cresta.weather.gateway.LocationGateway;
import org.cresta.weather.gateway.WeatherGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherServiceTest {
    private LocationGateway locationGateway;

    private WeatherGateway weatherGateway;

    private WeatherService weatherService;

    @BeforeEach
    public void setUp() throws IOException, InterruptedException, ApiException {
        this.locationGateway = mock(LocationGateway.class);
        this.weatherGateway = mock(WeatherGateway.class);
        weatherService = new WeatherService(locationGateway, weatherGateway);
        when(locationGateway.getLocation("fjnfjjk")).thenReturn(null);
        when(weatherGateway.getWeather(1.11,2.22)).thenReturn(Weather.builder().temp(99.99).build());

    }

    @Test
    public void givenLocationThatDoesNotExistShouldThrowNotFoundException() throws IOException, InterruptedException, ApiException {
        Exception exception = assertThrows(NotFoundException.class, () -> {
            assertThat(weatherService.getWeather("fjnfjjk")).isEqualTo("");
        });

    }
}
