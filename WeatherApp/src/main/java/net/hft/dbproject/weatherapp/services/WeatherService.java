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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);
    private static final String APIKEY = "8207b192ff2c645813be5259681c74d6";

    public static WeatherInformation getWeatherByCity(String cityName) {
        String configuredURL = "http://api.openweathermap.org/data/2.5/weather?q=XXX";
        configuredURL = configuredURL.replaceFirst("XXX", cityName);
        return json2Weather(configuredURL);
    }

    public static List<WeatherInformation> getWeathersByCity(String cityName) {
        String configuredURL = String.format("http://api.openweathermap.org/data/2.5/find?q=%s&type=like", cityName);
        return json2Weathers(configuredURL);
    }
    
    private static List<WeatherInformation> json2Weathers(String configuredURL) {
        List<WeatherInformation> result = new ArrayList<WeatherInformation>();
        URL url = null;
        HttpURLConnection urlConnect = null;
        InputStreamReader inputStream = null;
        try {
            url = new URL(configuredURL);
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(WeatherService.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOGGER.info("Requested URL: {} using Key: {}", url.toString(), APIKEY);
        try {
            urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setRequestMethod("GET");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(WeatherService.class.getName()).log(Level.SEVERE, null, ex);
        }
        urlConnect.setRequestProperty("Authorization", "APPID=" + APIKEY);
        urlConnect.setRequestProperty("Accept", "application/json");
        try {
            inputStream = new InputStreamReader(urlConnect.getInputStream());
            result = JSONParser.toWeathers(inputStream);
            inputStream.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(WeatherService.class.getName()).log(Level.SEVERE, null, ex);
        }

        urlConnect.disconnect();

        return result;
    }    

    private static WeatherInformation json2Weather(String configuredURL) {
        WeatherInformation tmp = new WeatherInformation();
        URL url = null;
        HttpURLConnection urlConnect = null;
        InputStreamReader inputStream = null;
        try {
            url = new URL(configuredURL);
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(WeatherService.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOGGER.info("Requested URL: {} using Key: {}", url.toString(), APIKEY);
        try {
            urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setRequestMethod("GET");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(WeatherService.class.getName()).log(Level.SEVERE, null, ex);
        }
        urlConnect.setRequestProperty("Authorization", "APPID=" + APIKEY);
        urlConnect.setRequestProperty("Accept", "application/json");
        try {
            inputStream = new InputStreamReader(urlConnect.getInputStream());
            tmp = JSONParser.toWeather(inputStream);
            inputStream.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(WeatherService.class.getName()).log(Level.SEVERE, null, ex);
        }

        urlConnect.disconnect();

        return tmp;
    }

}
