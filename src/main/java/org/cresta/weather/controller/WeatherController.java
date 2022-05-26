package org.cresta.weather.controller;

import com.google.maps.errors.ApiException;
import com.google.maps.errors.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.cresta.weather.domain.Weather;
import org.cresta.weather.domain.WeatherRequest;
import org.cresta.weather.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static java.time.temporal.ChronoUnit.MINUTES;

@AllArgsConstructor
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;
    private final UserMemoryRepository userMemoryRepository;

    @PostMapping("/hello")
    public ResponseEntity getWeather(@RequestBody WeatherRequest weatherRequest) throws IOException, InterruptedException, ApiException {
        try{
        Weather weather = weatherService.getWeather(weatherRequest.getLocation());
        UserData userData = UserData.builder()
                .userId(weatherRequest.getUser())
                .weather(weather)
                .time(LocalTime.now())
                .build();
        userMemoryRepository.saveOrUpdate(userData);
        return ResponseEntity.ok(weather);
        }
        catch (NotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("goodbye")
    public ResponseEntity getElapsedTime(@RequestBody WeatherRequest WeatherRequest){
        return ResponseEntity.ok(MINUTES.between(userMemoryRepository.find(WeatherRequest.getUser()).getTime(),LocalTime.now()));
    }

    @GetMapping("allRecords")
    public ResponseEntity getRecords(){
        return ResponseEntity.ok(userMemoryRepository.findAll());
    }
    @Repository
    static class UserMemoryRepository {
        private Map<String, UserData> userDataMap = new ConcurrentHashMap<>();

        UserData saveOrUpdate(UserData userData) {
            if (userData.getUserId() == null) {
                userData.setUserId(UUID.randomUUID().toString());
            }
            userDataMap.put(userData.getUserId(), userData);

            return userData;
        }

        Collection<UserData> findAll() {
            return userDataMap.values();
        }

        UserData find(String user){
            return userDataMap.get(user);
        }
    }

    @Data
    @Builder
    static class UserData {
        private String userId;
        private Weather weather;
        private LocalTime time;
    }
}
