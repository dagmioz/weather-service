package com.dagmioz.weather.providers.impl;

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

public class WeatherDataServiceOpenWeather implements IWeatherDataService {

    private final static String SERVICE_URL = "http://api.openweathermap.org/data/2.5/weather?APPID=63d25da6f3585bee81ba06d96cb5097a&units=metric&type=accurate&q=";

    WeatherData weatherData = new WeatherData();
    Location location = new Location();
    static ReadDataFromJson jreader = new ReadDataFromJson();

    public WeatherDataServiceOpenWeather() {
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

            JSONObject json = jreader.readJsonFromUrl(buildQueryUrl(location));
            if (json.get("cod").toString().equalsIgnoreCase("404")) {
                weatherData.setCod(json.get("cod").toString());
                weatherData.setCodMessage(json.get("message").toString());
            } else {
                weatherData.setCoord1(json.getJSONObject("coord").get("lon").toString());
                weatherData.setCoord2(json.getJSONObject("coord").get("lat").toString());

                String str = json.get("weather").toString();
                // System.out.println(str.substring(1, str.length()-1));
                JSONObject jsonStr = new JSONObject(str.substring(1, str.length() - 1));

                weatherData.setWeather_general_desc(jsonStr.get("main").toString());
                weatherData.setWeather_desc(jsonStr.get("description").toString());
                weatherData.setAvg_current_temp(json.getJSONObject("main").get("temp").toString());
                weatherData.setAvg_max_temp(json.getJSONObject("main").get("temp_max").toString());
                weatherData.setAvg_min_temp(json.getJSONObject("main").get("temp_min").toString());
                weatherData.setPressure(json.getJSONObject("main").get("pressure").toString());
                weatherData.setHumidity(json.getJSONObject("main").get("humidity").toString());
                weatherData.setWind_speed(json.getJSONObject("wind").get("speed").toString());
                weatherData.setClouds_percent(json.getJSONObject("clouds").get("all").toString());
                weatherData.setCity(json.get("name").toString());
                weatherData.setCountry(json.getJSONObject("sys").get("country").toString());
                weatherData.setSunrise(json.getJSONObject("sys").get("sunrise").toString());
                weatherData.setSunset(json.getJSONObject("sys").get("sunset").toString());

            }

            results.put(location, weatherData);
        }

        return results;
    }

    private String buildQueryUrl(Location location) {
        if (null != location && null != location.getCountry()) {
            return SERVICE_URL + location.getCity() + "," + location.getCountry();
        }
        return SERVICE_URL + location.getCity();
    }

}
