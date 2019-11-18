package com.example.openweather.view.cityforecast;

import com.example.openweather.data.dto.ForecastDto;
import com.example.openweather.model.City;

public interface CityForecastContract {

    interface View {

        void showLoadingLayout();

        void showContentLayout();

        void showErrorLayout();

        void updateForecast(ForecastDto dto);

    }

    interface Presenter {

        void setView(CityForecastContract.View view);

        void loadData(City city);

        void loadDataWithProgress(City city);

        void refreshUi();

        void onDestroy();

    }

}
