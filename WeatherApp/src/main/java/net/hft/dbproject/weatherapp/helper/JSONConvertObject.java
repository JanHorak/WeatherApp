/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.helper;

import java.util.ArrayList;
import java.util.List;
import net.hft.dbproject.weatherapp.entities.Location;

/**
 *
 * @author Jan
 */
public class JSONConvertObject {

    private int _id;

    private String name;

    private String country;

    private float lon;

    private float lat;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return name + ", " + country;
    }

    public static List<JSONConvertObject> toJSONConvertList(List<Location> list) {
        List<JSONConvertObject> result = new ArrayList<>();

        for (Location w : list) {
            JSONConvertObject j = new JSONConvertObject();
            j._id = w.getCityIdentifier();
            j.country = w.getCountryCode();
            j.name = w.getCityName();
            j.lat = w.getLat();
            j.lon = w.getLon();
            result.add(j);
        }
        return result;
    }

}
