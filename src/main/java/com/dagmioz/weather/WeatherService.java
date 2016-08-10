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
        System.out.println("args are" + args);
        Location inputLocation = new Location();
        if (args == null || args.length == 0) {
            inputLocation.setCity("Jerusalem");
        } else {
            inputLocation.setCity(args[0]);
        }
        if (args.length > 1) {
            inputLocation.setCountry(args[1]);
        }
        SearchForCity searcher = new SearchForCity();
        try {
            System.out.println("WeatherService.main: Weather in " + inputLocation.getCity() + ": " + searcher.runSearchForCity(inputLocation));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
