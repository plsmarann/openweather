package com.example.openweather.view.base;


import androidx.appcompat.app.AppCompatActivity;

import com.example.openweather.MainApplication;
import com.example.openweather.dagger.DiComponent;

public abstract class BaseActivity extends AppCompatActivity {

    private MainApplication getMainApplication() {
        return (MainApplication) getApplication();
    }

    protected DiComponent getDiComponent() {
        return getMainApplication().getDiComponent();
    }

}
