package com.weather.api.config;

import com.weather.api.models.CityTemperature;
import com.weather.api.scheduler.TemperatureUpdateScheduler;
import com.weather.api.services.CityTemperatureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DataLoader {

    private final TemperatureUpdateScheduler temperatureUpdateScheduler;

    public DataLoader(TemperatureUpdateScheduler temperatureUpdateScheduler) {
        this.temperatureUpdateScheduler = temperatureUpdateScheduler;
    }

    @Bean
    CommandLineRunner initDatabase(CityTemperatureService cityTemperatureService) {
        return args -> {
            loadCityData(cityTemperatureService);
            temperatureUpdateScheduler.updateWeatherData();
        };
    }

    private void loadCityData(CityTemperatureService cityTemperatureService) {
        Map<String, String> cityIdMap = new HashMap<>();
        cityIdMap.put("Tokyo", "1850147");
        cityIdMap.put("New York", "5128581");
        cityIdMap.put("London", "2643743");
        cityIdMap.put("Paris", "2988507");
        cityIdMap.put("Sydney", "2147714");
        cityIdMap.put("Dubai", "292223");
        cityIdMap.put("Rio de Janeiro", "3451190");
        cityIdMap.put("Mumbai", "1275339");
        cityIdMap.put("Beijing", "1816670");
        cityIdMap.put("Cape Town", "3369157");
        cityIdMap.put("Cairo", "360630");
        cityIdMap.put("Mexico City", "3530597");
        cityIdMap.put("Berlin", "2950159");
        cityIdMap.put("Bangkok", "1609350");
        cityIdMap.put("Toronto", "6167865");
        cityIdMap.put("Moscow", "524901");
        cityIdMap.put("Singapore", "1880252");
        cityIdMap.put("Buenos Aires", "3435910");
        cityIdMap.put("Istanbul", "745044");
        cityIdMap.put("Nairobi", "184745");

        List<CityTemperature> cityTemperatures = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Map.Entry<String, String> entry : cityIdMap.entrySet()) {
            String city = entry.getKey();
            String openWeatherMapId = entry.getValue();

            CityTemperature cityTemp = new CityTemperature();
            cityTemp.setCityName(city);
            cityTemp.setOpenWeatherMapId(openWeatherMapId);
            cityTemp.setTemperature(0.0);

            cityTemperatures.add(cityTemp);
        }

        cityTemperatureService.createCityTemperatures(cityTemperatures);
        System.out.println("Entered " + cityTemperatures.size() + " cities");
    }
}