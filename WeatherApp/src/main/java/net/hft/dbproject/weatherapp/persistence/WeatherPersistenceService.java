/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.persistence;

import net.hft.dbproject.weatherapp.entities.WeatherImage;

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
    
}
