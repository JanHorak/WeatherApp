/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.services;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import net.hft.dbproject.weatherapp.entities.Location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author admin
 */
public abstract class WeatherAPIConnection implements LocationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherAPIConnection.class);
    private static final String APIKEY = "8207b192ff2c645813be5259681c74d6";
    private static  String configuredURL;
    private static String configuredURLLike = "http://api.openweathermap.org/data/2.5/find?q=XXX&type=like";
    private static String configuredURLID = "http://api.openweathermap.org/data/2.5/weather?id=XXX";

    /**
     * Returns a List of Location which is rquested by the cityname.
     * The API will do a LIKE- Search. So its possible that the result of e.g.
     * 'London' will return multiple results.
     *
     * @param cityName
     * @return List of Location from the API
     */
    public static List<Location> requestCitiesByLike(String cityName) {
        configuredURL = configuredURLLike.replaceFirst("XXX", cityName);
        return json2Weather();
    }
    
    public static Location requestCityByID(int cityId) {
        configuredURL = configuredURLID.replaceFirst("XXX", String.valueOf(cityId));
        return json2Weather().get(0);
    }

    /**
     * Creates tje Connection to the API by using the {@link #APIKEY} and the
     * {@link #configuredURL}.
     *
     * @return
     */
    private static List<Location> json2Weather() {
        List<Location> result = new ArrayList<>();
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
            result = JSONParser.toLocationList(urlConnect.getInputStream());
        } catch (IOException ex) {
            LOGGER.error(ex.toString());
        }

        urlConnect.disconnect();

        return result;
    }

}
