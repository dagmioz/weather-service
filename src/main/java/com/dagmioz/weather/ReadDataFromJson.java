package com.dagmioz.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import com.dagmioz.weather.exceptions.WeatherProviderException;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadDataFromJson {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject readJsonFromUrl(String url) throws WeatherProviderException {
        InputStream is = null;
        try {
            is = new URL(url).openStream();
        } catch (IOException e) {
            throw new WeatherProviderException(String.format("Unable to connect to provider (url: %s)", url));
        }
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } catch (Exception e) {
            String exMessage = null;
            if (e.getClass().equals(IOException.class)) {
                exMessage = "Unable to read data from provider";
            } else if (e.getClass().equals(JSONException.class)) {
                exMessage = "Unable to parse json object from provider data";
            } else {
                exMessage = "Something went wrong";
            }
            throw new WeatherProviderException(exMessage);
        } finally {
            try {
                is.close();
            } catch (IOException e) {}
        }
    }

}
