package com.weather.api.services;

import com.weather.api.models.CityTemperature;
import com.weather.api.repositories.CityTemperatureRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityTemperatureService {
    private final CityTemperatureRepository cityTemperatureRepository;

    CityTemperatureService(CityTemperatureRepository cityTemperatureRepository) {
        this.cityTemperatureRepository = cityTemperatureRepository;
    }

    public Page<CityTemperature> getCityTemperatures(Pageable pageable) {
        return cityTemperatureRepository.findAll(pageable);
    }

    public List<CityTemperature> getAllCityTemperatures() {
        return cityTemperatureRepository.findAll();
    }

    public void createCityTemperature(CityTemperature ct) {
        cityTemperatureRepository.save(ct);
    }

    public void updateTemperature(CityTemperature cityTemp, Double newTemperature) {
        cityTemp.setTemperature(newTemperature);

        cityTemperatureRepository.save(cityTemp);
    }

}
