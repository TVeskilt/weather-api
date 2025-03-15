package com.weather.api.scheduler;

import com.weather.api.config.DataLoader.DataLoadCompletedEvent;
import com.weather.api.models.CityTemperature;
import com.weather.api.services.CityTemperatureService;
import com.weather.api.services.OpenWeatherMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemperatureUpdateScheduler {

    private static final Logger logger = LoggerFactory.getLogger(TemperatureUpdateScheduler.class);
    private final OpenWeatherMapService openWeatherMapService;
    private final CityTemperatureService cityTemperatureService;

    public TemperatureUpdateScheduler(OpenWeatherMapService openWeatherMapService,
                                      CityTemperatureService cityTemperatureService) {
        this.openWeatherMapService = openWeatherMapService;
        this.cityTemperatureService = cityTemperatureService;
    }

    @EventListener
    public void onDataLoadCompleted(DataLoadCompletedEvent event) {
        logger.info("Initial data load completed, starting first weather update");
        updateWeatherData();
    }

    @Scheduled(cron = "0 0 0 * * ?") // Run at midnight every day
    public void updateWeatherData() {
        logger.info("Starting scheduled weather update");

        List<CityTemperature> cities = cityTemperatureService.getAllCityTemperatures();

        for (CityTemperature city : cities) {
            try {
                String cityId = city.getOpenWeatherMapId();
                Double temperature = openWeatherMapService.getTemperatureForCityId(cityId);

                if (temperature != null) {
                    cityTemperatureService.updateTemperature(city, temperature);
                    logger.info("Updated temperature for {}: {}°C", city.getCityName(), temperature);
                }
            } catch (Exception e) {
                logger.error("Failed to update temperature for {}: {}", city.getCityName(), e.getMessage());
            }
        }

        logger.info("Weather update completed");
    }
}