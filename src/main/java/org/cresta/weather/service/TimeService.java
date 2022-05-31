package org.cresta.weather.service;

import lombok.AllArgsConstructor;
import org.cresta.weather.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;

@Service
@AllArgsConstructor
public class TimeService {
    private final WeatherRepository weatherRepository;

    public long getElapsedTime(String user){
        return SECONDS.between(weatherRepository.findByUser(user).getCreated_at(), LocalDateTime.now());
    }
}
