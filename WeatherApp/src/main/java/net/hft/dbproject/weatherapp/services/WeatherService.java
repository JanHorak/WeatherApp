package net.hft.dbproject.weatherapp.services;

import java.util.List;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;

/**
 *
 * @author Jan
 */
public interface WeatherService {
    
    public WeatherInformation getWeatherByCityID(int id);
    
}
