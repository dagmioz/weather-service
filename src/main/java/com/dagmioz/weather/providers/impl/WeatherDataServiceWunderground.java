package com.dagmioz.weather.providers.impl;

/**
 * Created by oz on 18/07/16.
 */

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.dagmioz.weather.ReadDataFromJson;
import com.dagmioz.weather.exceptions.WeatherDataServiceException;
import com.dagmioz.weather.exceptions.WeatherProviderException;
import com.dagmioz.weather.model.Location;
import com.dagmioz.weather.model.WeatherData;
import com.dagmioz.weather.providers.api.IWeatherDataService;
import com.sun.javafx.binding.StringFormatter;
import org.json.JSONArray;
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
            boolean hasArrayOfCityResults = responseJson.has("results");
            if (hasError || doesNotHaveCurrentObservation) {
                String message = null;
                if (hasError) {
                    JSONObject error = json.getJSONObject("error");
                    message = String.format("received error from provider service: \"%s\"", error.getString("description"));
                }
                else if (hasArrayOfCityResults)
                {
                    JSONArray ArrayOfCityResults = json.getJSONArray("results");
                    for(int n=0 ; n > ArrayOfCityResults.length(); n++)
                    {
                        JSONObject object = ArrayOfCityResults.getJSONObject(n);
                        message = String.format("To many results for your'e search , please add country to the search \"@s\"", object.getString("country_name"));

                    }
                }
                else {
                    message = "Service didn't return data";
                }
                throw new WeatherDataServiceException(message);
            } else {

                try {
                    JSONObject currentObservation = json.getJSONObject("current_observation");
                    weatherData.setCity(currentObservation.getJSONObject("display_location").getString("full"));
                    weatherData.setLatitude(currentObservation.getJSONObject("observation_location").getString("latitude"));
                    weatherData.setLongitude(currentObservation.getJSONObject("observation_location").getString("longitude"));
                    weatherData.setElevation(currentObservation.getJSONObject("display_location").getString("elevation"));
                    weatherData.setObservation_time(currentObservation.getString("observation_time"));
                    weatherData.setLocal_time_rfc822(currentObservation.getString("local_time_rfc822"));
                    weatherData.setWeather(currentObservation.getString("weather"));
                    weatherData.setTemp_c(currentObservation.optString("temp_c"));
                    //weatherData.setTemp_c(currentObservation.get("temp_c").toString());
                    weatherData.setFeelslike_string(currentObservation.getString("feelslike_string"));
                    weatherData.setRelative_humidity(currentObservation.getString("relative_humidity"));
                    //weatherData.setWind_kph(currentObservation.get("wind_kph").toString());
                    weatherData.setWind_kph(currentObservation.optString("wind_kph"));
                } catch (JSONException e) {
                    String message = ("unable to retrieve one or more of the json object's, please check the Wunderground API");
                    System.out.println(message);
                    e.printStackTrace();
                }
            }
            results.put(location, weatherData);
        }
        return results;
    }

    private String buildQueryUrl(Location location) {
        if (null != location && null != location.getCountry()) {
            return SERVICE_URL + location.getCountry() + "/" + location.getCity() + ".json";
        }
        return SERVICE_URL + location.getCity() + ".json";
    }
}