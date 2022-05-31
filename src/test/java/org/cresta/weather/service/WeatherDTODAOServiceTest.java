package org.cresta.weather.service;

import com.google.maps.errors.ApiException;
import com.google.maps.errors.NotFoundException;
import org.cresta.weather.gateway.LocationGateway;
import org.cresta.weather.gateway.WeatherGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherDTODAOServiceTest {
    @Mock
    private LocationGateway locationGateway;

    @Mock
    private WeatherGateway weatherGateway;

    @Autowired
    private WeatherService weatherService;

    @BeforeEach
    public void setUp() throws IOException, InterruptedException, ApiException {
        when(locationGateway.getLocation("fjnfjjk")).thenReturn(null);
//        when(weatherGateway.getWeather(1.11,2.22)).thenReturn(Weather.builder().temp(99.99).build());

    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(weatherService).isNotNull();
    }

    @Test
    public void givenLocationThatDoesNotExistShouldThrowNotFoundException() throws IOException, InterruptedException, ApiException {
        Exception exception = assertThrows(NotFoundException.class, () -> {
            assertThat(weatherService.getWeather("fjnfjjk", "rhoiugfheor")).isEqualTo("");
        });

    }
}
