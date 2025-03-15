package com.weather.api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OpenWeatherMapConfig {

    @Getter
    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Value("${openweathermap.api.url:https://api.openweathermap.org/data/2.5}")
    private String apiUrl;

    @Bean
    public WebClient openWeatherMapClient() {
        return WebClient.builder()
            .baseUrl(apiUrl)
            .build();
    }
}