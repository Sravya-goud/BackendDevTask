package com.example.weatherbyip.service;

import com.example.weatherbyip.exception.ExternalApiException;
import com.example.weatherbyip.model.Weather;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j

public class WeatherService {
	
	@Value("${weather.api.key}")
    private String weatherApiKey;

    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    @Cacheable(value = "weather", key = "#city", unless = "#result == null")
    public Weather getWeatherByCity(String city) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = String.format(WEATHER_URL, city, weatherApiKey);
            var response = restTemplate.getForObject(url, WeatherApiResponse.class);

            if (response == null) {
                throw new ExternalApiException("Weather API returned null for city: " + city);
            }

            return new Weather(
                    response.getMain().getTemp(),
                    response.getMain().getHumidity(),
                    response.getWeather()[0].getDescription()
            );
        } catch (Exception e) {
            log.error("Failed to fetch weather for city: {}", city, e);
            throw new ExternalApiException("Error fetching weather for city: " + city);
        }
    }

    // Inner class for parsing response
    private static class WeatherApiResponse {
        private Main main;
        private WeatherDescription[] weather;

        // Getters & Setters
        public Main getMain() { return main; }
        public void setMain(Main main) { this.main = main; }

        public WeatherDescription[] getWeather() { return weather; }
        public void setWeather(WeatherDescription[] weather) { this.weather = weather; }
    }

    private static class Main {
        private double temp;
        private int humidity;

        public double getTemp() { return temp; }
        public void setTemp(double temp) { this.temp = temp; }

        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }
    }

    private static class WeatherDescription {
        private String description;

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

}
