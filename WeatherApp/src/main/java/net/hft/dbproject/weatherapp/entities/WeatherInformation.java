/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author admin
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "WeatherInformation.findLastThreeInfo", query = "SELECT i FROM WeatherInformation i ORDER BY i.id DESC"),
    @NamedQuery(name = "WeatherInformation.findAll", query = "SELECT i FROM WeatherInformation i ORDER BY i.id DESC"),
    @NamedQuery(name = "WeatherInformation.findThreeByName", query = "SELECT i FROM WeatherInformation i WHERE i.cityName = :cityName ORDER BY i.id DESC"),
    @NamedQuery(name = "WeatherInformation.find", query = "SELECT i FROM WeatherInformation i")
})
public class WeatherInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String cityName;

    private String weatherDescription;

    @NotNull
    @ManyToOne(targetEntity = Temperature.class ,cascade = CascadeType.ALL)
    private Temperature temperature;
    
    @ManyToOne(targetEntity = AppUser.class, cascade = CascadeType.ALL)
    private AppUser searcher;

    @NotNull
    private int cityIdentifier;

    @Transient
    private String countryCode;

    @Transient
    private float lat;

    @Transient
    private float lon;

    @NotNull
    @ManyToOne(targetEntity = WeatherImage.class)
    private WeatherImage image;

    public WeatherInformation(int ident, String cityName, String countryCode, Temperature temp, float lat, float lon) {
        this.cityIdentifier = ident;
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.temperature = temp;
        this.lat = lat;
        this.lon = lon;
    }

    public WeatherInformation() {

    }

    public WeatherInformation(String cityName) {
        this.cityName = cityName;
    }

    public void setCityName(String cname) {
        this.cityName = cname;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setweatherDescription(String cdescription) {
        this.weatherDescription = cdescription;
    }

    public String getweatherDescription() {
        return this.weatherDescription;
    }

    @Override
    public String toString() {
        return "WeatherInformation{" + "id=" + id + ", cityName=" + cityName + ", weatherDescription=" + weatherDescription + ", temperature=" + temperature.toString() + ",image=" + image + ",ident=" + cityIdentifier + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public WeatherImage getImage() {
        return image;
    }

    public void setImage(WeatherImage image) {
        this.image = image;
    }

    public int getCityIdentifier() {
        return cityIdentifier;
    }

    public void setCityIdentifier(int cityIdentifier) {
        this.cityIdentifier = cityIdentifier;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public AppUser getSearcher() {
        return searcher;
    }

    public void setSearcher(AppUser searcher) {
        this.searcher = searcher;
    }
    
    

}
