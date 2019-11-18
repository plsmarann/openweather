package com.example.openweather.util;

import com.example.openweather.R;

public final class WeatherUtils {

    private WeatherUtils() {
    }

    public static String getFormattedTemperature(Double temp) {
        return String.valueOf(temp.intValue()) + "º";
    }

    public static int getWeatherIconResourceFromString(String string) {

        switch (string) {
            case "01d": {
                return R.drawable.ic_sun;
            }

            case "01n": {
                return R.drawable.ic_moon;
            }

            case "10d": {
                return R.drawable.ic_rain;
            }
            case "10n": {
                return R.drawable.ic_rain;
            }
            case "13d": {
                return R.drawable.ic_snow;
            }
            case "13n": {
                return R.drawable.ic_snow;
            }

            case "50d": {
                return R.drawable.ic_foggy;
            }
            case "50n": {
                return R.drawable.ic_foggy;
            }


            case "03d": {
                return R.drawable.ic_clouds;
            }

            case "p02d": {
                return R.drawable.ic_partly_cloudy_day;
            }

            case "02n": {
                return R.drawable.ic_partly_cloudy_night;
            }

            default: {
                return R.drawable.ic_sun;
            }
        }

    }

}
