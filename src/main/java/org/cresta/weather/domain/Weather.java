package org.cresta.weather.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Weather {
    private Double temp;

    private String feels_like;

    private String pressure;

    private String humidity;
}
