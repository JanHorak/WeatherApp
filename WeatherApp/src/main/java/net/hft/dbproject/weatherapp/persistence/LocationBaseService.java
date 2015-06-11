/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.persistence;

import java.util.List;
import net.hft.dbproject.weatherapp.entities.WeatherImage;
import net.hft.dbproject.weatherapp.entities.Location;

/**
 *
 * @author Jan
 */
public interface LocationBaseService {
    
    public WeatherImage getImageByIconID(int id);
    public List<Location> getFirstThreeLocations();
    public List<Location> findAll();
    public List<Location> getThreeLocationsByName(String cityName);
    
}
