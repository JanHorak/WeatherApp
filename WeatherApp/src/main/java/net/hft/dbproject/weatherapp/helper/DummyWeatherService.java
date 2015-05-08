/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.helper;

import java.util.ArrayList;
import java.util.List;
import net.hft.dbproject.weatherapp.entities.Temperature;
import net.hft.dbproject.weatherapp.entities.WeatherImage;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;
import net.hft.dbproject.weatherapp.persistence.WeatherBaseService;
import net.hft.dbproject.weatherapp.persistence.WeatherPersistenceService;
import net.hft.dbproject.weatherapp.services.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jan
 */
public class DummyWeatherService implements WeatherService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DummyWeatherService.class);

    
    @Override
    public List<WeatherInformation> getWeatherListByCityName(String name) {
        List<WeatherInformation> result = new ArrayList<>();
        WeatherImage wi = new WeatherImage();
        WeatherBaseService weatherBaseService = new WeatherPersistenceService();
        
        wi = weatherBaseService.getImageByIconID(800);
        
        WeatherInformation wi1 = new WeatherInformation();
        wi1.setCityName("London");
        wi1.setImage(wi);
        Temperature t1 = new Temperature();
        t1.setAverageTemp(123.00);
        t1.setMaxTemp(133.00);
        t1.setMinTemp(111.00);
        wi1.setTemperature(t1);
        wi1.setZipCode("00215");
        wi1.setweatherDescription("weatherDesc");
        
        WeatherInformation wi2 = new WeatherInformation();
        wi2.setCityName("London2");
        wi2.setImage(wi);
        Temperature t2 = new Temperature();
        t2.setAverageTemp(123.00);
        t2.setMaxTemp(133.00);
        t2.setMinTemp(111.00);
        wi2.setTemperature(t1);
        wi2.setZipCode("00215");
        wi2.setweatherDescription("weatherDesc");
        
        WeatherInformation wi3 = new WeatherInformation();
        wi3.setCityName("LondonDerry");
        wi3.setImage(wi);
        Temperature t3 = new Temperature();
        t3.setAverageTemp(123.00);
        t3.setMaxTemp(133.00);
        t3.setMinTemp(111.00);
        wi3.setTemperature(t1);
        wi3.setZipCode("00215");
        wi3.setweatherDescription("weatherDesc");
        
        
        result.add(wi1);
        result.add(wi2);
        result.add(wi3);
        
        LOGGER.warn("== DummyData are loaded == \nPlease replace this dummydata by real implementation as soon as possible");
        
        return result;
        
        
    }
    
}
