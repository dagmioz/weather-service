package com.dagmioz.weather.model;

import java.io.Serializable;
import java.lang.reflect.Field;

public class WeatherData implements Serializable {

    private String coord1;
    private String coord2;
    private String weather_general_desc;
    private String weather_desc;
    private String avg_current_temp;
    private String avg_max_temp;
    private String avg_min_temp;
    private String pressure;
    private String humidity;
    private String wind_speed;
    private String clouds_percent;
    private String city;
    private String country;
    private String cod;
    private String codMessage;
    private String sunset;
    private String sunrise;

    //objects declaration for Wunderground provider

    private String full;
    private String latitude;
    private String longitude;
    private String elevation;
    private String observation_time;
    private String local_time_rfc822;
    private String weather;
    private String temp_c;
    private String feelslike_string;
    private String relative_humidity;
    private String wind_kph;

    public  String getFull() {
        return full;
    }public void   setFull(String full) {
        this.full = full;
    }public String getLatitude() {
        return latitude;
    }public void   setLatitude(String latitude) {
        this.latitude = latitude;
    }public String getLongitude() {
        return longitude;
    }public void   setLongitude(String longitude) {
        this.longitude = longitude;
    }public String getElevation() {
        return elevation;
    }public void   setElevation(String elevation) {
        this.elevation = elevation;
    }public String getObservation_time() {
        return observation_time;
    }public void   setObservation_time(String observation_time) {
        this.observation_time = observation_time;
    }public String getLocal_time_rfc822() {
        return local_time_rfc822;
    }public void   setLocal_time_rfc822(String local_time_rfc822) {
        this.local_time_rfc822 = local_time_rfc822;
    }public String getWeather() {
        return weather;
    }public void   setWeather(String weather) {
        this.weather = weather;
    }public String getTemp_c() {
        return temp_c;
    }public void   setTemp_c(String temp_c) {
        this.temp_c = temp_c;
    }public String getFeelslike_string() {
        return feelslike_string;
    }public void   setFeelslike_string(String feelslike_string) {
        this.feelslike_string = feelslike_string;
    }public String getRelative_humidity() {
        return relative_humidity;
    }public void   setRelative_humidity(String relative_humidity) {
        this.relative_humidity = relative_humidity;
    }public String getWind_kph() {
        return wind_kph;
    }public void   setWind_kph(String wind_kph) {
        this.wind_kph = wind_kph;
    }

//for open weather map
    public String getCoord1() {
        return coord1;
    }

    public void setCoord1(String coord1) {
        this.coord1 = coord1;
    }

    public String getCoord2() {
        return coord2;
    }

    public void setCoord2(String coord2) {
        this.coord2 = coord2;
    }

    public String getWeather_general_desc() {
        return weather_general_desc;
    }

    public void setWeather_general_desc(String weather_general_desc) {
        this.weather_general_desc = weather_general_desc;
    }

    public String getWeather_desc() {
        return weather_desc;
    }

    public void setWeather_desc(String weather_desc) {
        this.weather_desc = weather_desc;
    }

    public String getAvg_current_temp() {
        return avg_current_temp;
    }

    public void setAvg_current_temp(String avg_current_temp) {
        this.avg_current_temp = avg_current_temp;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(this.getClass().getName());
        result.append(" Object {");
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for (Field field : fields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                //requires access to private field:
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }

    public String getAvg_max_temp() {
        return avg_max_temp;
    }

    public void setAvg_max_temp(String avg_max_temp) {
        this.avg_max_temp = avg_max_temp;
    }

    public String getAvg_min_temp() {
        return avg_min_temp;
    }

    public void setAvg_min_temp(String avg_min_temp) {
        this.avg_min_temp = avg_min_temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getClouds_percent() {
        return clouds_percent;
    }

    public void setClouds_percent(String clouds_percent) {
        this.clouds_percent = clouds_percent;
    }

    public WeatherData(String coord1, String coord2,
                       String weather_general_desc, String weather_desc,
                       String avg_current_temp, String avg_max_temp, String avg_min_temp,
                       String pressure, String humidity, String wind_speed,
                       String clouds_percent, String sunset, String sunrise) {
        super();
        this.coord1 = coord1;
        this.coord2 = coord2;
        this.weather_general_desc = weather_general_desc;
        this.weather_desc = weather_desc;
        this.avg_current_temp = avg_current_temp;
        this.avg_max_temp = avg_max_temp;
        this.avg_min_temp = avg_min_temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind_speed = wind_speed;
        this.clouds_percent = clouds_percent;
        this.sunset = sunset;
        this.sunrise = sunrise;
    }

    public WeatherData() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getCodMessage() {
        return codMessage;
    }

    public void setCodMessage(String codMessage) {
        this.codMessage = codMessage;
    }
}
