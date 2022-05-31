package org.cresta.weather.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.cresta.weather.repository.Weather;

@Data
public class WeatherResponse {
    @JsonProperty("main")
    public Weather weather;
}
