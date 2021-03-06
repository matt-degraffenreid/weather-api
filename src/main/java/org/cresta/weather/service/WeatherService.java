package org.cresta.weather.service;

import com.google.maps.model.LatLng;
import lombok.AllArgsConstructor;
import org.cresta.weather.domain.WeatherDTO;
import org.cresta.weather.gateway.LocationGateway;
import org.cresta.weather.gateway.WeatherGateway;
import org.cresta.weather.repository.Weather;
import org.cresta.weather.repository.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeatherService {
    private final LocationGateway locationGateway;

    private final WeatherGateway weatherGateway;

    private final WeatherRepository weatherRepository;

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    public WeatherDTO getWeather(String user, String location) throws Exception {
        LatLng coords = locationGateway.getLocation(location);
        Weather weather = weatherGateway.getWeather(coords.lat, coords.lng).toBuilder().user(user).build();

        log.info("Saving: " + weather.toString());
        weatherRepository.save(weather);

        return weather.toDTO();
    }
}
