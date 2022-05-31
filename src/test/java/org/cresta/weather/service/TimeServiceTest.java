package org.cresta.weather.service;

import org.cresta.weather.repository.Weather;
import org.cresta.weather.repository.WeatherRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TimeServiceTest {
    @InjectMocks
    private TimeService timeService;

    @Mock
    private WeatherRepository weatherRepository;

    private final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2022,1, 1,0,0,0);

    private final LocalDateTime LOCAL_DATE_TIME_FIXED = LocalDateTime.of(2022,1, 1,0,0,1);
    private final String USER = "John Doe";
    @BeforeEach
    public void setUp(){
        Weather weather = Weather.builder()
                .user(USER)
                .humidity(60)
                .pressure(60)
                .feels_like(60)
                .temp(60)
                .created_at(LOCAL_DATE_TIME)
                .build();

        when(weatherRepository.findByUser(USER)).thenReturn(weather);
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(timeService).isNotNull();
    }

    @Test
    public void shouldReturnTimeInSecondsSinceInitialRequest(){
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(LOCAL_DATE_TIME_FIXED);
            assertThat(timeService.getElapsedTime(USER)).isEqualTo(1);
        }
    }
}
