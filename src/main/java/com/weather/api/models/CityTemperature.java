package com.weather.api.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city_temperature")
@EqualsAndHashCode(callSuper = false)
public class CityTemperature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String cityName;
    private Double temperature;
    private String openWeatherMapId;
}
