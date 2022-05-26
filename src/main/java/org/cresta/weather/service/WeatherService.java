package org.cresta.weather.service;


import com.google.maps.errors.ApiException;
import com.google.maps.errors.NotFoundException;
import com.google.maps.model.LatLng;
import lombok.AllArgsConstructor;
import org.cresta.weather.domain.Weather;
import org.cresta.weather.gateway.LocationGateway;
import org.cresta.weather.gateway.WeatherGateway;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class WeatherService {
    private final LocationGateway locationGateway;

    private final WeatherGateway weatherGateway;

    public Weather getWeather(String location) throws IOException, InterruptedException, ApiException {
        LatLng coords = locationGateway.getLocation(location);
        if(coords == null)
            throw new NotFoundException("The Location entered could not be found");
        return weatherGateway.getWeather(coords.lat, coords.lng);
    }
}
