/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.services;

import java.util.ArrayList;
import java.util.List;
import net.hft.dbproject.weatherapp.entities.Location;
import net.hft.dbproject.weatherapp.helper.JSONConvertObject;

/**
 *
 * @author Jan
 */
public class OnlineLocationFinder implements Locationfinder {

    private static List<Location> locationCache = new ArrayList<>();

    @Override
    public List<JSONConvertObject> getLocationsByCityName(String name) {
        List<JSONConvertObject> result = null;
        locationCache = WeatherAPIConnection.requestCitiesByLike(name);
        result = JSONConvertObject.toJSONConvertList(locationCache);
        return result;
    }
    
    public static Location getWeatherFromCacheById(int id){
        Location result = new Location();
        for (Location wi : locationCache){
            if (wi.getCityIdentifier() == id){
                result = wi;
                break;
            }
        }
        return result;
    }

}
