package org.cresta.weather.controller;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import org.cresta.weather.domain.Weather;
import org.cresta.weather.domain.WeatherRequest;
import org.cresta.weather.gateway.LocationGateway;
import org.cresta.weather.gateway.WeatherGateway;
import org.cresta.weather.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherControllerTest {

    private LocationGateway locationGateway;

    private WeatherGateway weatherGateway;

    private WeatherService weatherService;

    private WeatherController weatherController;

    private WeatherController.UserMemoryRepository userMemoryRepository;

    @BeforeEach
    public void setUp() throws IOException, InterruptedException, ApiException {
        this.locationGateway = mock(LocationGateway.class);
        this.weatherGateway = mock(WeatherGateway.class);
        this.userMemoryRepository = new WeatherController.UserMemoryRepository();
        this.weatherService = new WeatherService(this.locationGateway, this.weatherGateway);
        this.weatherController = new WeatherController(this.weatherService, userMemoryRepository);
        when(locationGateway.getLocation("Denver")).thenReturn(new LatLng(1.11, 2.22));
        when(weatherGateway.getWeather(1.11,2.22)).thenReturn(Weather.builder().temp(99.99).build());
    }

    @Test
    public void givenDataWhenStoringShouldReturnOkResponse() throws IOException, InterruptedException, ApiException {
        WeatherRequest weatherRequest = WeatherRequest.builder().user("123456").location("Denver").build();
        assertThat(this.weatherController.getWeather(weatherRequest).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
