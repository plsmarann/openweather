package com.example.openweather.data.repository.impl;

import android.content.Context;

import com.example.openweather.R;
import com.example.openweather.data.dto.ForecastDto;
import com.example.openweather.data.repository.ForecastRepository;
import com.example.openweather.data.service.ForecastService;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Singleton
public class ForecastRepositoryImpl implements ForecastRepository {

    private ForecastService service;
    private Context context;

    @Inject
    public ForecastRepositoryImpl(ForecastService service, Context context) {
        this.service = service;
        this.context = context;
    }

    @Override
    public Observable<ForecastDto> getForecast(Double latitude, Double longitude) {
        Observable<ForecastDto> forecast = service
                .getForecast(
                        latitude,
                        longitude, context.getString(R.string.FORECAST_API_KEY)
                        );

        return forecast.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
