package com.example.weatherbyip.service;

import com.example.weatherbyip.model.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class WeatherByIpService {
	
	private final LocationService locationService;
    private final WeatherService weatherService;

    public WeatherResponse getWeatherDetails(String ip) {
        // Step 1: Get location from IP
        var location = locationService.getLocationByIp(ip);

        // Step 2: Get weather by city name
        var weather = weatherService.getWeatherByCity(location.getCity());

        // Step 3: Return aggregated response
        return new WeatherResponse(ip, location, weather);
    }

}
