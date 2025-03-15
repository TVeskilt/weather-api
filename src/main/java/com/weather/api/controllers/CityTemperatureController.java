package com.weather.api.controllers;

import com.weather.api.models.CityTemperature;
import com.weather.api.services.CityTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city-temperature")
public class CityTemperatureController {
    private final CityTemperatureService cityTemperatureService;

    @Autowired
    public CityTemperatureController(CityTemperatureService cityTemperatureService) {
        this.cityTemperatureService = cityTemperatureService;
    }

    @GetMapping
    public ResponseEntity<List<CityTemperature>> getCityTemperatures() {
        List<CityTemperature> res = cityTemperatureService.getCityTemperatures();
        return ResponseEntity.ok(res);
    }

}
