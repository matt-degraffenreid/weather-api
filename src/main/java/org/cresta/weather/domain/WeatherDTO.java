package org.cresta.weather.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherDTO {
    private Double temp;

    private Double feels_like;

    private Double pressure;

    private Double humidity;
}
