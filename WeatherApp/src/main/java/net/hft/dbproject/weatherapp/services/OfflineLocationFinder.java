/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.services;

import java.util.ArrayList;
import java.util.List;
import net.hft.dbproject.weatherapp.helper.CityBulk;
import net.hft.dbproject.weatherapp.helper.JSONConvertObject;

/**
 *
 * @author Jan
 */
public class OfflineLocationFinder implements Locationfinder {

    @Override
    public List<JSONConvertObject> getLocationsByCityName(String name) {
        List<JSONConvertObject> result = new ArrayList<>();
        JSONConvertObject[] memDB = CityBulk.getInstance().getCities();
        for (JSONConvertObject obj : memDB) {
            if (obj.getName().contains(name)) {
                result.add(obj);
            }
        }
        return result;
    }

}
