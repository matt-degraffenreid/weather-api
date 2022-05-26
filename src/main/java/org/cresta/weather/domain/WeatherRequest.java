package org.cresta.weather.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherRequest {
    public String user;
    public String location;
}
