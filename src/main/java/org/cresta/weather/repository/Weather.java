package org.cresta.weather.repository;

import lombok.*;
import org.cresta.weather.domain.WeatherDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Builder(toBuilder = true)
@Entity
@Data
public class Weather {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String user;

    private double temp;

    private double feels_like;

    private double pressure;

    private double humidity;

    private LocalDateTime created_at;

    private Weather(Long id, String user, double temp, double feels_like, double pressure, double humidity, LocalDateTime created_at) {
        this.id = id;
        this.user = user;
        this.temp = temp;
        this.feels_like = feels_like;
        this.pressure = pressure;
        this.humidity = humidity;
        this.created_at = isNull(created_at) ? LocalDateTime.now(): created_at;
    }

    public Weather() {

    }

    @Override
    public String toString() {
        return String.format(
                "Weather[user_id=%s, temp='%.2f', feels_like='%.2f', pressure='%.2f', humidity='%.2f']",
                this.user,
                this.temp,
                this.feels_like,
                this.pressure,
                this.humidity);
    }

    public WeatherDTO toDTO(){
        return WeatherDTO.builder()
                .temp(this.temp)
                .feels_like(this.feels_like)
                .pressure(this.pressure)
                .humidity(this.humidity)
                .build();
    }
}
