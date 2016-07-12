package com.dagmioz.weather.providers.api;

import java.io.IOException;
import java.util.Map;

import com.dagmioz.weather.WeatherDataServiceException;
import com.dagmioz.weather.model.Location;
import com.dagmioz.weather.model.WeatherData;
import org.json.JSONException;

public interface IWeatherDataService {
    public WeatherData getWeatherData(Location location) throws WeatherDataServiceException, JSONException, IOException;
    public Map<Location, WeatherData> getWeatherData(Location... locations) throws WeatherDataServiceException, JSONException, IOException;
}
