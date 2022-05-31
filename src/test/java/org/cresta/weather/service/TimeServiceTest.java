package org.cresta.weather.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TimeServiceTest {
    @Autowired
    private TimeService TimeService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(TimeService).isNotNull();
    }
}
