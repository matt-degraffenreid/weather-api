package org.cresta.weather.service;

import lombok.AllArgsConstructor;
import org.cresta.weather.repository.Weather;
import org.cresta.weather.repository.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;

@Service
@AllArgsConstructor
public class TimeService {
    private static final Logger log = LoggerFactory.getLogger(TimeService.class);

    private final WeatherRepository weatherRepository;

    public long getElapsedTime(String user){
        Weather userWeatherData = weatherRepository.findByUser(user);
        log.info("Retrieved: " + userWeatherData.toString());
        return SECONDS.between(userWeatherData.getCreated_at(), LocalDateTime.now());
    }
}
