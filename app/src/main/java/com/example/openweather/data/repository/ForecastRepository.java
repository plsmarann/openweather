package com.example.openweather.data.repository;

import com.example.openweather.data.dto.ForecastDto;

import rx.Observable;

public interface ForecastRepository {

    Observable<ForecastDto> getForecast(Double latitude, Double longitude);

}
