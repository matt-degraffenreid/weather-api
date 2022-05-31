package org.cresta.weather.service;

import lombok.AllArgsConstructor;
import org.cresta.weather.repository.Weather;
import org.cresta.weather.repository.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class TimeService {
    private static final Logger log = LoggerFactory.getLogger(TimeService.class);

    private final WeatherRepository weatherRepository;

    public long getElapsedTime(String user) throws Exception {
        Weather userWeatherData = weatherRepository.findByUser(user);
        if(isNull(userWeatherData))
            throw new Exception("User not found");
        log.info("Retrieved: " + userWeatherData);
        return SECONDS.between(userWeatherData.getCreated_at(), LocalDateTime.now());
    }
}
