package com.example.openweather.dagger;

import com.example.openweather.dagger.module.ApplicationModule;
import com.example.openweather.dagger.module.DbModule;
import com.example.openweather.dagger.module.NetworkModule;
import com.example.openweather.dagger.module.RepositoryModule;
import com.example.openweather.dagger.module.ServiceModule;
import com.example.openweather.view.cityadd.CityAddDialogFragment;
import com.example.openweather.view.cityforecast.CityForecastFragment;
import com.example.openweather.view.citylist.CityListFragment;
import com.example.openweather.view.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        ServiceModule.class,
        RepositoryModule.class,
        DbModule.class
})
public interface DiComponent {

    void inject(MainActivity activity);

    void inject(CityListFragment fragment);

    void inject(CityForecastFragment fragment);

    void inject(CityAddDialogFragment fragment);

}
