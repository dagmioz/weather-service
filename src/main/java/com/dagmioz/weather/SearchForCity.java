package com.dagmioz.weather;

import com.dagmioz.weather.model.Location;
import com.dagmioz.weather.model.WeatherData;
import com.dagmioz.weather.providers.api.IWeatherDataService;
import org.json.JSONException;

public class SearchForCity {
    public WeatherData runSearchForCity(Location loc) throws JSONException, Exception {
        IWeatherDataService service = WeatherDataServiceFactory.getWeatherData(WeatherDataServiceFactory.WeatherServiceProviders.OPEN_WEATHER_MAP);

        WeatherData data = service.getWeatherData(loc);
        return data;

    }

}
