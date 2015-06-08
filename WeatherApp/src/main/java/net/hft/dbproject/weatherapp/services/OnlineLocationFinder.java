/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.services;

import java.util.ArrayList;
import java.util.List;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;
import net.hft.dbproject.weatherapp.helper.JSONConvertObject;

/**
 *
 * @author Jan
 */
public class OnlineLocationFinder implements Locationfinder {

    private static List<WeatherInformation> weatherInfoCache = new ArrayList<>();

    @Override
    public List<JSONConvertObject> getLocationsByCityName(String name) {
        List<JSONConvertObject> result = null;
        weatherInfoCache = WeatherAPIConnection.requestCitiesByLike(name);
        result = JSONConvertObject.toJSONConvertList(weatherInfoCache);
        return result;
    }
    
    public static WeatherInformation getWeatherFromCacheById(int id){
        WeatherInformation result = new WeatherInformation();
        for (WeatherInformation wi : weatherInfoCache){
            if (wi.getCityIdentifier() == id){
                result = wi;
                break;
            }
        }
        return result;
    }

}
