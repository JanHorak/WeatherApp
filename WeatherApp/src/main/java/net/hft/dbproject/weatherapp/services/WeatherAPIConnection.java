/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.services;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;

import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author admin
 */
public abstract class WeatherAPIConnection implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherAPIConnection.class);
    private static final String APIKEY = "8207b192ff2c645813be5259681c74d6";
    private static String configuredURL = "http://api.openweathermap.org/data/2.5/weather?q=XXX";

    public static WeatherInformation getWeatherByCity(String cityName) {
        configuredURL = configuredURL.replaceFirst("XXX", cityName);
        return json2Weather();
    }

    private static WeatherInformation json2Weather() {
        WeatherInformation tmp = new WeatherInformation();
        URL url = null;
        HttpURLConnection urlConnect = null;
        InputStreamReader inputStream = null;
        try {
            url = new URL(configuredURL);
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(WeatherAPIConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOGGER.info("Requested URL: {} using Key: {}", url.toString(), APIKEY);
        try {
            urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setRequestMethod("GET");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(WeatherAPIConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        urlConnect.setRequestProperty("Authorization", "APPID=" + APIKEY);
        urlConnect.setRequestProperty("Accept", "application/json");
        try {
            inputStream = new InputStreamReader(urlConnect.getInputStream());
            tmp = JSONParser.toWeather(inputStream);
            inputStream.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(WeatherAPIConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        urlConnect.disconnect();

        return tmp;
    }

}
