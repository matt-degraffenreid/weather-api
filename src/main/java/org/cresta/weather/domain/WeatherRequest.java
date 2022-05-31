package org.cresta.weather.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class WeatherRequest {
    @NotBlank
    public String user;
    @NotBlank
    public String location;
}
