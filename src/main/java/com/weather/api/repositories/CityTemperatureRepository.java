package com.weather.api.repositories;

import com.weather.api.models.CityTemperature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityTemperatureRepository extends JpaRepository<CityTemperature, Long> {
}
