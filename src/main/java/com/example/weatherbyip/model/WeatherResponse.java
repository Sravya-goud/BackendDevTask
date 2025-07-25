package com.example.weatherbyip.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WeatherResponse {
	private String ip;
    private Location location;
    private Weather weather;
}
