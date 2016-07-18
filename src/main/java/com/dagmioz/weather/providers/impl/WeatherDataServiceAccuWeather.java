package com.dagmioz.weather.providers.impl;

/**
 * Created by oz on 18/07/16.
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.dagmioz.weather.ReadDataFromJson;
import com.dagmioz.weather.WeatherDataServiceException;
import com.dagmioz.weather.model.Location;
import com.dagmioz.weather.model.WeatherData;
import com.dagmioz.weather.providers.api.IWeatherDataService;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataServiceAccuWeather implements IWeatherDataService {

    private final static String SERVICE_URL = "dataservice.accuweather.com/locations/v1/search?apikey=paPyuYGUKqxE6ALLMRvq7YT9XZ20b0BJ&language=en-us&q=";

    WeatherData weatherData = new WeatherData();
    Location location = new Location();
    static ReadDataFromJson jreader = new ReadDataFromJson();

    public WeatherDataServiceAccuWeather() {
        super();
        // TODO Auto-generated constructor stub
    }

    public WeatherData getWeatherData(Location location)
            throws WeatherDataServiceException, JSONException, IOException {
        return this.getWeatherData(new Location[]{location}).get(location);
    }

    public Map<Location, WeatherData> getWeatherData(Location... locations) throws WeatherDataServiceException, JSONException, IOException {
        Map<Location, WeatherData> results = new HashMap<Location, WeatherData>();
}

