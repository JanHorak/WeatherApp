package net.hft.dbproject.weatherapp.services;

import net.hft.dbproject.weatherapp.entities.Location;

/**
 *
 * @author Jan
 */
public interface LocationService {
    
    public Location getLocationByCityID(int id);
    
}
