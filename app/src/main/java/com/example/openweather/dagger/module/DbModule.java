package com.example.openweather.dagger.module;

import android.content.Context;

import com.example.openweather.data.db.helper.DatabaseHelper;
import com.example.openweather.model.City;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelper(Context context, @Named("DefaultCities") List<City> cities) {
        return new DatabaseHelper(context, cities);
    }

    @Provides
    @Singleton
    ConnectionSource provideConnectionSource(DatabaseHelper databaseHelper) {
        return databaseHelper.getConnectionSource();
    }

    @Provides
    @Singleton
    @Named("CityDao")
    RuntimeExceptionDao<City, Integer> provideCityDao(DatabaseHelper databaseHelper) {
        return databaseHelper.getRuntimeExceptionDao(City.class);
    }

    @Provides
    @Singleton
    @Named("DefaultCities")
    List<City> provideDefaultCities() {
        List<City> cities = new ArrayList<>(4);

        City kathmandu = new City();
        kathmandu.name = "Kathmandu";
        kathmandu.fullDescription = "Kathmandu, Nepal";
        kathmandu.latitude = 27.7172;
        kathmandu.longitude = 85.3240;
        cities.add(kathmandu);

        City pokhara = new City();
        pokhara.name = "Pokhara";
        pokhara .fullDescription = "Pokhara, Nepal";
        pokhara .latitude = 28.2096;
        pokhara .longitude = 83.9856;
        cities.add(pokhara);

        City newDh = new City();
        newDh.name = "New Delhi";
        newDh.fullDescription = "New Delhi, India";
        newDh.latitude = 28.6139;
        newDh.longitude = 77.2090;
        cities.add(newDh);

        City newYork = new City();
        newYork.name = "New York";
        newYork.fullDescription = "New York, US";
        newYork.latitude = 40.730610;
        newYork.longitude = -73.935242;
        cities.add(newYork);



        return cities;
    }

}
