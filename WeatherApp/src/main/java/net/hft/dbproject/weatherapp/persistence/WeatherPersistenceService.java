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
public class WeatherPersistenceService extends DataAccess implements WeatherBaseService {

    @Override
    public WeatherImage getImageByIconID(int id) {
        WeatherImage result;
        setup();
        openConnection();
        result = (WeatherImage) em.createNamedQuery("WeatherImage.findByIconID").setParameter("iconId", id).getSingleResult();
        shutDown();
        return result;
    }

    // Startup
    @Override
    public List<WeatherInformation> getFirstThreeInfo() {
        List<WeatherInformation> result;
        setup();
        openConnection();
        result = (List<WeatherInformation>) em.createNamedQuery("WeatherInformation.findLastThreeInfo").setMaxResults(3).getResultList();
        shutDown();
        return result;
    }

    // Selection
    @Override
    public List<WeatherInformation> getThreeInfoByName(String cityName) {
        List<WeatherInformation> result;
        setup();
        openConnection();
        result = (List<WeatherInformation>) em.createNamedQuery("WeatherInformation.findThreeByName").setParameter("cityName", cityName).setMaxResults(3).getResultList();
        shutDown();
        return result;
    }
    
    // Combobox
    @Override
    public List<WeatherInformation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
