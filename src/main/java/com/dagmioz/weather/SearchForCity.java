package com.dagmioz.weather;

import com.dagmioz.weather.model.Location;
import com.dagmioz.weather.model.WeatherData;
import com.dagmioz.weather.providers.api.IWeatherDataService;
import org.json.JSONException;

public class SearchForCity {
    private IWeatherDataService service = null;

    public SearchForCity() {
        this.service = WeatherDataServiceFactory.getWeatherData(WeatherDataServiceFactory.WeatherServiceProviders.OPEN_WEATHER_MAP);
    }

    public SearchForCity(IWeatherDataService aService) {
        this.service = aService;
    }

    public WeatherData runSearchForCity(Location loc) throws JSONException, Exception {
        WeatherData data = service.getWeatherData(loc);
        return data;

    }

    public IWeatherDataService getService() {
        return service;
    }

    public void setService(IWeatherDataService service) {
        this.service = service;
    }
}
