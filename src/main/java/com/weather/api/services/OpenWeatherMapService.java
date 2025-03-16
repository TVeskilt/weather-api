package com.weather.api.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherMapService {

    private static final Logger logger = LoggerFactory.getLogger(OpenWeatherMapService.class);

    private final RestTemplate restTemplate;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Value("${openweathermap.api.url:https://api.openweathermap.org/data/2.5}")
    private String apiUrl;

    public OpenWeatherMapService() {
        this.restTemplate = new RestTemplate();
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 2))
    public Double getTemperatureForCityId(String cityId) {
        try {
            String url = String.format("%s/weather?id=%s&units=metric&appid=%s",
                apiUrl, cityId, apiKey);

            JsonNode response = restTemplate.getForObject(url, JsonNode.class);

            if (response != null && response.has("main") && response.get("main").has("temp")) {
                return response.get("main").get("temp").asDouble();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}