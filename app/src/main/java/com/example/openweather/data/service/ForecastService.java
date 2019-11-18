package com.example.openweather.data.service;


import com.example.openweather.data.dto.ForecastDto;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ForecastService {

    @GET("weather")
    Observable<ForecastDto> getForecast(
                                        @Query("lat") Double latitude,
                                        @Query("lon") Double longitude,
    @Query("APPID") String api);
}
