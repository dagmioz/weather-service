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

}

