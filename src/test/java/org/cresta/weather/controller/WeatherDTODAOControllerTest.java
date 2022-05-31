package org.cresta.weather.controller;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import org.cresta.weather.domain.WeatherRequest;
import org.cresta.weather.gateway.LocationGateway;
import org.cresta.weather.gateway.WeatherGateway;
import org.cresta.weather.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.IOException;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@SpringBootTest
public class WeatherDTODAOControllerTest {
    @Mock
    private LocationGateway locationGateway;

    @Mock
    private WeatherGateway weatherGateway;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherController weatherController;

    @BeforeEach
    public void setUp() throws IOException, InterruptedException, ApiException {
        when(locationGateway.getLocation("Denver")).thenReturn(new LatLng(1.11, 2.22));
//        when(weatherGateway.getWeather(1.11,2.22)).thenReturn(Weather.builder().temp(99.99).build());
    }
    @Test
    public void contextLoads() throws Exception {
        assertThat(weatherController).isNotNull();
    }
    @Test
    public void givenDataWhenStoringShouldReturnOkResponse() throws IOException, InterruptedException, ApiException {
        WeatherRequest weatherRequest = WeatherRequest.builder().user("123456").location("Denver").build();
        assertThat(this.weatherController.getWeather(weatherRequest).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
