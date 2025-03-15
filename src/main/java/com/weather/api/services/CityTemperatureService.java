package com.weather.api.services;

import com.weather.api.models.CityTemperature;
import com.weather.api.repositories.CityTemperatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityTemperatureService {
    private final CityTemperatureRepository cityTemperatureRepository;

    CityTemperatureService(CityTemperatureRepository cityTemperatureRepository){
        this.cityTemperatureRepository = cityTemperatureRepository;
    }

    public List<CityTemperature> getCityTemperatures() {
        return cityTemperatureRepository.findAll();
    }

    public CityTemperature createCityTemperature(CityTemperature ct) {
        return cityTemperatureRepository.save(ct);
    }
}
