package com.example.weatherbyip.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Weather {
	private double temperature;
    private int humidity;
    private String description;


}
