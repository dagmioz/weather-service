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

public class WeatherDataServiceWunderground implements IWeatherDataService {

    private final static String SERVICE_URL = "http://api.wunderground.com/api/d2b185ea0a367130/conditions/q/"; //After the "q/" type the name of the city you want to search

    WeatherData weatherData = new WeatherData();
    Location location = new Location();
    static ReadDataFromJson jreader = new ReadDataFromJson();

    public WeatherDataServiceWunderground() {
        super();
        // TODO Auto-generated constructor stub
    }

    public WeatherData getWeatherData(Location location)
            throws WeatherDataServiceException, JSONException, IOException {
        return this.getWeatherData(new Location[]{location}).get(location);
    }

    public Map<Location, WeatherData> getWeatherData(Location... locations) throws WeatherDataServiceException, JSONException, IOException {
        Map<Location, WeatherData> results = new HashMap<Location, WeatherData>();

        for (Location location : locations) {
            WeatherData weatherData = new WeatherData();
        }

        JSONObject json = jreader.readJsonFromUrl(buildQueryUrl(location));
        if (json.get("type").toString().equalsIgnoreCase("querynotfound")) {
            weatherData.setCod(json.get("type").toString());
            weatherData.setCodMessage(json.get("description").toString());
        } else {
            String str = json.get("current_observation").toString();
            JSONObject jsonStr = new JSONObject(str.substring(1, str.length() - 1));
        }
        results.put(location, weatherData);
        return results;
    }

    private String buildQueryUrl(Location location) {
        if (null != location && null != location.getCountry()) {
            return SERVICE_URL + location.getCity() + "," + location.getCountry();
        }
        return SERVICE_URL + location.getCity();
    }
}


