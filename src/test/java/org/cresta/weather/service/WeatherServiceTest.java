package org.cresta.weather.service;

import com.google.maps.errors.ApiException;
import com.google.maps.errors.NotFoundException;
import com.google.maps.model.LatLng;
import org.cresta.weather.domain.WeatherDTO;
import org.cresta.weather.gateway.LocationGateway;
import org.cresta.weather.gateway.WeatherGateway;
import org.cresta.weather.repository.Weather;
import org.cresta.weather.repository.WeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherServiceTest {
    @Mock
    private LocationGateway locationGateway;

    @Mock
    private WeatherGateway weatherGateway;

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherService weatherService;

    private final String USER = "John Doe";

    private final String BAD_LOCATION = "neoivneoiv";

    private final String GOOD_LOCATION = "Denver";

    @Test
    public void contextLoads(){
        assertThat(weatherService).isNotNull();
    }

    @Test
    public void shouldReturnAWeatherDTOWhenAllDataIsGood() throws Exception {
        Weather weather= Weather.builder()
                .temp(99.99)
                .feels_like(99.99)
                .pressure(99.99)
                .humidity(99.99)
                .build();

        when(locationGateway.getLocation(GOOD_LOCATION)).thenReturn(new LatLng(1.00,2.00));
        when(weatherGateway.getWeather(1.00,2.00))
                .thenReturn(weather);
        when(weatherRepository.save(weather)).thenReturn(weather);
        WeatherDTO expectedResponse = WeatherDTO.builder()
                .temp(99.99)
                .feels_like(99.99)
                .pressure(99.99)
                .humidity(99.99)
                .build();

        assertThat(weatherService.getWeather(USER,GOOD_LOCATION)).isEqualTo(expectedResponse);
    }
    @Test
    public void givenLocationThatDoesNotExistShouldThrowNotFoundException() throws Exception {
        when(locationGateway.getLocation(BAD_LOCATION)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> {
            weatherService.getWeather(USER, BAD_LOCATION);
        });
    }

    @Test
    public void givenLocationGatewayIsDownShouldThrowException() throws Exception {
        when(locationGateway.getLocation(GOOD_LOCATION)).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> {
            weatherService.getWeather(USER, BAD_LOCATION);
        });
    }
}
