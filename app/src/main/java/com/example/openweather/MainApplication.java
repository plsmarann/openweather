package com.example.openweather;

import android.app.Application;
import  com.example.openweather.dagger.DaggerDiComponent;

import com.example.openweather.dagger.DiComponent;
import com.example.openweather.dagger.module.ApplicationModule;
import com.example.openweather.dagger.module.NetworkModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainApplication extends Application {

    private DiComponent diComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initDagger();
        initCalligraphy();
    }

    private void initDagger() {
        diComponent = DaggerDiComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(getString(R.string.BASE_URL)))
                .build();
    }

    private void initCalligraphy() {
        CalligraphyConfig config = new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/roboto/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
        CalligraphyConfig.initDefault(config);
    }

    public DiComponent getDiComponent() {
        return diComponent;
    }
}
