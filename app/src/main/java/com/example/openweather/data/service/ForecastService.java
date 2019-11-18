package com.example.openweather.data.service;


import com.example.openweather.data.dto.ForecastDto;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ForecastService {

    @GET("weather/{latitude},{longitude}")
    Observable<ForecastDto> getForecast(
                                        @Path("latitude") double latitude,
                                        @Path("longitude") double longitude);
}
