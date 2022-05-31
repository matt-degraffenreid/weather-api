package org.cresta.weather.controller;

import com.google.maps.errors.ApiException;
import com.google.maps.errors.NotFoundException;
import lombok.AllArgsConstructor;
import org.cresta.weather.domain.WeatherDTO;
import org.cresta.weather.domain.WeatherRequest;
import org.cresta.weather.service.TimeService;
import org.cresta.weather.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    private final TimeService timeService;

    @PostMapping("/hello")
    public ResponseEntity getWeather(@RequestBody WeatherRequest weatherRequest) throws IOException, InterruptedException, ApiException {
        try{
        WeatherDTO weatherDTO = weatherService.getWeather(weatherRequest.getUser(), weatherRequest.getLocation());
        return ResponseEntity.ok(weatherDTO);
        }
        catch (NotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PostMapping("goodbye")
    public ResponseEntity getElapsedTime(@RequestBody WeatherRequest weatherRequest){
        return ResponseEntity.ok(timeService.getElapsedTime(weatherRequest.getUser()));
    }
}
