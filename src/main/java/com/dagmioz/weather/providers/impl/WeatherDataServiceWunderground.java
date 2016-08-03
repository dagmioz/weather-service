package com.dagmioz.weather.providers.impl;

/**
 * Created by oz on 18/07/16.
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.dagmioz.weather.ReadDataFromJson;
import com.dagmioz.weather.exceptions.WeatherDataServiceException;
import com.dagmioz.weather.exceptions.WeatherProviderException;
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
            throws WeatherDataServiceException, WeatherProviderException {
        return this.getWeatherData(new Location[]{location}).get(location);
    }

    public Map<Location, WeatherData> getWeatherData(Location... locations) throws WeatherDataServiceException, WeatherProviderException {
        Map<Location, WeatherData> results = new HashMap<Location, WeatherData>();

        for (Location location : locations) {
            WeatherData weatherData = new WeatherData();

            JSONObject json = jreader.readJsonFromUrl(buildQueryUrl(location));
            JSONObject responseJson = json.getJSONObject("response");
            boolean hasError = json.has("error");
            boolean doesNotHaveCurrentObservation = !json.has("current_observation");
            if (hasError || doesNotHaveCurrentObservation) {
                String message = null;
                if (hasError) {
                    JSONObject error = json.getJSONObject("error");
                    message = String.format("received error from provider service: \"%s\"", error.getString("description"));
                } else {
                    message = "Service didn't return data";
                }
                throw new WeatherDataServiceException(message);
            } else {
                JSONObject currentObservation = json.getJSONObject("current_observation");//use try and cache
                weatherData.setCity(currentObservation.getJSONObject("display_location").get("full").toString());
                weatherData.setLatitude(currentObservation.getJSONObject("observation_location").get("latitude").toString());
                weatherData.setLongitude(currentObservation.getJSONObject("observation_location").get("longitude").toString());
                weatherData.setElevation(currentObservation.getJSONObject("display_location").get("elevation").toString());
                weatherData.setObservation_time(currentObservation.get("observation_time").toString());
                weatherData.setLocal_time_rfc822(currentObservation.get("local_time_rfc822").toString());
                weatherData.setWeather(currentObservation.get("weather").toString());
                weatherData.setTemp_c(currentObservation.get("temp_c").toString());
                weatherData.setFeelslike_string(currentObservation.get("feelslike_string").toString());
                weatherData.setRelative_humidity(currentObservation.get("relative_humidity").toString());
                weatherData.setWind_kph(currentObservation.get("wind_kph").toString());






            }
            results.put(location, weatherData);
        }
        return results;
    }

    private String buildQueryUrl(Location location) {
        if (null != location && null != location.getCountry()) {
            return SERVICE_URL + location.getCountry() + "/"+ location.getCity() + ".json";
        }
        return SERVICE_URL + location.getCity() + ".json";
    }
}