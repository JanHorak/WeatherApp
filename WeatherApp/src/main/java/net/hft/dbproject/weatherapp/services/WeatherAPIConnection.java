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
public class WeatherAPIConnection implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherAPIConnection.class);
    private static final String APIKEY = "8207b192ff2c645813be5259681c74d6";
    private static String configuredURL = "http://api.openweathermap.org/data/2.5/weather?q=XXX,de";

    @Override
    public List<WeatherInformation> getWeatherListByCityName(String name) {
        configuredURL = configuredURL.replaceFirst("XXX", name);
        return json2Weather();
    }
    
    private static List<WeatherInformation> json2Weather() {
        List<WeatherInformation> result = new ArrayList<WeatherInformation>();
        URL url = null;
        HttpURLConnection urlConnect = null;
        try {
            url = new URL(configuredURL);
        } catch (MalformedURLException ex) {
            LOGGER.error(ex.toString());
        }
        LOGGER.info("Requested URL: {} using Key: {}", url.toString(), APIKEY);
        try {
            urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setRequestMethod("GET");
        } catch (IOException ex) {
            LOGGER.error(ex.toString());
        }
        urlConnect.setRequestProperty("Authorization", "APPID=" + APIKEY);
        urlConnect.setRequestProperty("Accept", "application/json");
        try {
            JSONParser jsonParser = new JSONParser();
            WeatherInformation weatherInfo = jsonParser.toWeather(urlConnect.getInputStream());
            if (weatherInfo != null)
            {
                result.add(weatherInfo);
            }
        } catch (IOException ex) {
            LOGGER.error(ex.toString());
        }

        urlConnect.disconnect();

        return result;
    }    


}
