package com.example.openweather.data.dto;

import com.example.openweather.model.weather.Currently;
import com.example.openweather.model.weather.Daily;

import java.io.Serializable;

public class ForecastDto implements Serializable {

    public double latitude;
    public double longitude;
    public String timezone;
    public Double offset;
    public Currently currently;
    public Daily daily;

}
