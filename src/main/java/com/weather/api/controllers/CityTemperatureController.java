package com.weather.api.controllers;

import com.weather.api.models.CityTemperature;
import com.weather.api.services.CityTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city-temperature")
public class CityTemperatureController {
    private final CityTemperatureService cityTemperatureService;

    @Autowired
    public CityTemperatureController(CityTemperatureService cityTemperatureService) {
        this.cityTemperatureService = cityTemperatureService;
    }

    @GetMapping
    public ResponseEntity<Page<CityTemperature>> getCityTemperatures(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int pageSize,
        @RequestParam(defaultValue = "cityName") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
            Sort.by(sortBy).descending() :
            Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<CityTemperature> pagedResult = cityTemperatureService.getCityTemperatures(pageable);

        return ResponseEntity.ok(pagedResult);
    }
}