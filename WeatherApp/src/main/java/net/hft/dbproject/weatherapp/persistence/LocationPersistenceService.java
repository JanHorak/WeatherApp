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
public class LocationPersistenceService extends DataAccess implements LocationBaseService {

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
    public List<Location> getFirstThreeLocations() {
        List<Location> result;
        setup();
        openConnection();
        result = (List<Location>) em.createNamedQuery("Location.findLastThreeInfo").setMaxResults(3).getResultList();
        shutDown();
        return result;
    }

    // Selection
    @Override
    public List<Location> getThreeLocationsByName(String cityName) {
        List<Location> result;
        setup();
        openConnection();
        result = (List<Location>) em.createNamedQuery("Location.findThreeByName").setParameter("cityName", cityName).setMaxResults(3).getResultList();
        shutDown();
        return result;
    }

    // Combobox
    @Override
    public List<Location> findAll() {
        List<Location> result;
        setup();
        openConnection();
        result = (List<Location>) em.createNamedQuery("Location.find").getResultList();
        shutDown();
        return result;
    }

}
