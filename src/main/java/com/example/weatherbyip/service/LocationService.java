package com.example.weatherbyip.service;

import com.example.weatherbyip.exception.ExternalApiException;
import com.example.weatherbyip.model.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j

public class LocationService {
	private static final String IP_API_URL = "http://ip-api.com/json/";

    public Location getLocationByIp(String ip) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = IP_API_URL + ip;
            var response = restTemplate.getForObject(url, LocationApiResponse.class);

            if (response == null || !"success".equalsIgnoreCase(response.getStatus())) {
                throw new ExternalApiException("Failed to fetch location for IP: " + ip);
            }

            return new Location(response.getCity(), response.getCountry());
        } catch (Exception e) {
            log.error("Error fetching location", e);
            throw new ExternalApiException("Error fetching location for IP: " + ip);
        }
    }

    // Inner class for parsing response
    private static class LocationApiResponse {
        private String status;
        private String city;
        private String country;

        // Getters & Setters
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }

        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
    }

}
