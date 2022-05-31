package org.cresta.weather.gateway;

import org.cresta.weather.domain.WeatherResponse;

import org.cresta.weather.repository.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.util.Objects.requireNonNull;

@Component
public class WeatherGateway {
    @Value("${spring.weatherSource.url}")
    private String openWeatherUrl;

    @Value("${spring.weatherSource.apiKey}")
    private String openWeatherApiKey;
    private final RestTemplate restTemplate;

    public WeatherGateway(RestTemplateBuilder restTemplateBuilder){
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        messageConverters.add(converter);
        this.restTemplate = restTemplateBuilder.messageConverters(messageConverters).build();
    }
    public Weather getWeather(Double lat, Double lng){
        try{
            String url = String.format(this.openWeatherUrl, lat, lng, this.openWeatherApiKey);
            WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);
            return requireNonNull(weatherResponse).getWeather();
        }
        catch(Exception ex){
            return null;
        }
    }
}
