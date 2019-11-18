package com.example.openweather.view.cityforecast;

import com.example.openweather.data.dto.ForecastDto;
import com.example.openweather.data.repository.ForecastRepository;
import com.example.openweather.model.City;

import javax.inject.Inject;

public class CityForecastPresenter implements CityForecastContract.Presenter {

    private ForecastRepository forecastRepository;
    private ForecastDto forecastDto;
    private CityForecastContract.View view;

    @Inject
    public CityForecastPresenter(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    public void setView(CityForecastContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData(City city) {
        if (view != null) {
            Double lat = city.latitude;
            Double lon = city.longitude;
            forecastRepository.getForecast(lat, lon).subscribe(dto -> {
                forecastDto = dto;
                refreshUi();
            }, throwable -> {
                throwable.printStackTrace();
                view.showErrorLayout();
            });
        }
    }

    @Override
    public void loadDataWithProgress(City city) {
        if (view != null) {
            view.showLoadingLayout();
        }

        loadData(city);
    }

    @Override
    public void refreshUi() {
        if (view != null) {
            view.updateForecast(forecastDto);
            view.showContentLayout();
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }

}
