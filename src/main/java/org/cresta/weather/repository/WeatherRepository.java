package org.cresta.weather.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {
    Weather findByUser(String user);
}
