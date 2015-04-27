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
import net.hft.dbproject.weatherapp.entities.Weather;

import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author admin
 */
public class WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);

    public static Weather json2Weather(URL myUrl, String key) {
        Weather tmp = new Weather();
        URL url = null;
        HttpURLConnection urlConnect = null;
        InputStreamReader inputStream = null;
        url = myUrl;
        LOGGER.info("Requested URL: {} using Key: {}", myUrl.toString(), key);
        try {
            urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setRequestMethod("GET");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(WeatherService.class.getName()).log(Level.SEVERE, null, ex);
        }
        urlConnect.setRequestProperty("Authorization", "APPID=" + key);
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
