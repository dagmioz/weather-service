package com.dagmioz.weather.exceptions;

public class WeatherDataServiceException extends Exception {
    private static final long serialVersionUID = 1997753363232807009L;

    public WeatherDataServiceException() {
    }


    public WeatherDataServiceException(String message) {

        super(message);

    }

    public WeatherDataServiceException(Throwable cause) {

        super(cause);

    }


    public WeatherDataServiceException(String message, Throwable cause) {

        super(message, cause);

    }


    public WeatherDataServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);

    }
}
