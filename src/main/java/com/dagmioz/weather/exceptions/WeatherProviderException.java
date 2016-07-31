package com.dagmioz.weather.exceptions;

/**
 * Created by Oz on 30/07/2016.
 * This exception will throw when connection to provider failes
 */
public class WeatherProviderException extends Exception {
    public WeatherProviderException() {
        super();
    }

    public WeatherProviderException(String message) {
        super(message);
    }

    public WeatherProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeatherProviderException(Throwable cause) {
        super(cause);
    }

    protected WeatherProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
