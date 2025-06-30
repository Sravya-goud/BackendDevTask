package com.example.weatherbyip.controller;

import com.example.weatherbyip.model.WeatherResponse;
import com.example.weatherbyip.service.WeatherByIpService;
import com.example.weatherbyip.util.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather-by-ip")
@RequiredArgsConstructor

public class WeatherController {
	
	private final WeatherByIpService weatherByIpService;

    @GetMapping
    public ResponseEntity<WeatherResponse> getWeatherByIp(
            @RequestParam(required = false)
            @Pattern(regexp = "^$|^((25[0-5]|(2[0-4]|1\\d|[1-9]|)[0-9])\\.(?!$)){3}(25[0-5]|(2[0-4]|1\\d|[1-9]|)[0-9])$",
                     message = "Invalid IP format")
            String ip,
            HttpServletRequest request
    ) {
        // If IP not passed, extract it from the request header
        if (ip == null || ip.isBlank()) {
            ip = IpUtils.extractClientIp(request);
        }

        WeatherResponse response = weatherByIpService.getWeatherDetails(ip);
        return ResponseEntity.ok(response);
    }

}
