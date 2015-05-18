/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.helper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import net.hft.dbproject.weatherapp.entities.Temperature;
import net.hft.dbproject.weatherapp.entities.WeatherImage;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;
import net.hft.dbproject.weatherapp.persistence.WeatherBaseService;
import net.hft.dbproject.weatherapp.persistence.WeatherPersistenceService;
import net.hft.dbproject.weatherapp.services.JSONParser;
import net.hft.dbproject.weatherapp.services.WeatherAPIConnection;
import net.hft.dbproject.weatherapp.services.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jan
 */
public class DummyWeatherService implements WeatherService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DummyWeatherService.class);
    private static final String APIKEY = "8207b192ff2c645813be5259681c74d6";
    private static String configuredURL = "http://api.openweathermap.org/data/2.5/find?q=XXX&type=like";

    @Override
    public List<WeatherInformation> getWeatherListByCityName(String name) {
        configuredURL = configuredURL.replaceFirst("XXX", name);
        return json2Weather();
    }
    
//    //@Override
//    public List<WeatherInformation> getWeatherListByZipCode(String zipCode) {
//        configuredURL = "http://api.openweathermap.org/data/2.5/weather?zip=XXX,de";
//        configuredURL = configuredURL.replaceFirst("XXX", zipCode);
//        return json2Weather();
//    }

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
            result = jsonParser.toWeatherList(urlConnect.getInputStream());
        } catch (IOException ex) {
            LOGGER.error(ex.toString());
        }

        urlConnect.disconnect();

        return result;
    }    
}
