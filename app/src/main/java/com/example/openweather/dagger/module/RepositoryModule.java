package com.example.openweather.dagger.module;

import android.content.Context;

import com.example.openweather.data.repository.CityRepository;
import com.example.openweather.data.repository.ForecastRepository;
import com.example.openweather.data.repository.impl.CityRepositoryImpl;
import com.example.openweather.data.repository.impl.ForecastRepositoryImpl;
import com.example.openweather.data.service.ForecastService;
import com.example.openweather.model.City;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    CityRepository provideCityRepository(Context context, @Named("CityDao") RuntimeExceptionDao<City, Integer> cityDao) {
        return new CityRepositoryImpl(context, cityDao);
    }

    @Provides
    ForecastRepository provideForecastRepository(ForecastService service, Context context) {
        return new ForecastRepositoryImpl(service, context);
    }

}
