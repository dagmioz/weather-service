package com.dagmioz.weather;

import com.dagmioz.weather.model.Location;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Oz on 12/07/2016.
 */
public class WeatherService {

    public static void main(String[] args) {
        System.out.println(String.format("WeatherService.main: %s", Arrays.toString(args)));
        Location london = new Location();
        london.setCity(Optional.of(args[0]).orElse("Jerusalem"));
        if (args.length > 1) {
            london.setCountry(args[1]);
        }
        SearchForCity searcher = new SearchForCity();
        try {
            System.out.println("WeatherService.main: Weather in " + london.getCity() + ": " + searcher.runSearchForCity(london));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
