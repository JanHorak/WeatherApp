/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.services;

import java.util.List;
import net.hft.dbproject.weatherapp.helper.JSONConvertObject;

/**
 *
 * @author Jan
 */
public interface Locationfinder {
    
    public List<JSONConvertObject> getLocationsByCityName(String name);
    
}
