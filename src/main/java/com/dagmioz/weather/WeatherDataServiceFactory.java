package com.dagmioz.weather;

import com.dagmioz.weather.providers.api.IWeatherDataService;
import com.dagmioz.weather.providers.impl.WeatherDataServiceWunderground;
import com.dagmioz.weather.providers.impl.WeatherDataServiceOpenWeather;

public class WeatherDataServiceFactory {
    private static WeatherDataServiceFactory firstInstance = null;

    private WeatherDataServiceFactory() {
    }

    public static WeatherDataServiceFactory getInstance() {
        if (firstInstance == null) {
            firstInstance = new WeatherDataServiceFactory();
        }
        return firstInstance;
    }

    public static IWeatherDataService getWeatherData(WeatherServiceProviders providerKey) {
        switch (providerKey) {
            case OPEN_WEATHER_MAP:
                return new WeatherDataServiceOpenWeather();
            case W_UNDERGROUND:
                return new WeatherDataServiceWunderground();
            default:
                break;
        }

        return new WeatherDataServiceOpenWeather();
    }

    public enum WeatherServiceProviders {
        OPEN_WEATHER_MAP,
        W_UNDERGROUND
    }
}
