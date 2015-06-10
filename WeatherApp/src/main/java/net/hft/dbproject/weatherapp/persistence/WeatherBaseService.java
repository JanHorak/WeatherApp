/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.persistence;

import java.util.List;
import net.hft.dbproject.weatherapp.entities.WeatherImage;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;

/**
 *
 * @author Jan
 */
public interface WeatherBaseService {
    
    public WeatherImage getImageByIconID(int id);
    public List<WeatherInformation> getFirstThreeInfo();
    public List<WeatherInformation> findAll();
    public List<WeatherInformation> getThreeInfoByName(String cityName);
    
}
