/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import net.hft.dbproject.weatherapp.trigger.CounterTrigger;

/**
 *
 * @author admin
 */
@Entity
@EntityListeners({
    CounterTrigger.class
})
@NamedQueries({
    @NamedQuery(name = "Location.findLastThreeInfo", query = "SELECT i FROM Location i ORDER BY i.id DESC"),
    @NamedQuery(name = "Location.findAllOrderdByTime", query = "SELECT i FROM Location i ORDER BY i.requestTime DESC"),
    @NamedQuery(name = "Location.findThreeByName", query = "SELECT i FROM Location i WHERE i.cityName = :cityName ORDER BY i.requestTime DESC"),
    @NamedQuery(name = "Location.findAll", query = "SELECT i FROM Location i")
})
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String cityName;

    @NotNull
    private String weatherDescription;

    @NotNull
    @ManyToOne(targetEntity = Temperature.class, cascade = CascadeType.ALL)
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date requestTime;

    @NotNull
    @ManyToOne(targetEntity = WeatherImage.class)
    private WeatherImage image;

    public Location(int ident, String cityName, String countryCode, Temperature temp, float lat, float lon) {
        this.cityIdentifier = ident;
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.temperature = temp;
        this.lat = lat;
        this.lon = lon;
    }

    public Location() {
    }

    public Location(String cityName) {
        this.cityName = cityName;
    }

    public void setCityName(String cname) {
        this.cityName = cname;
    }

    public String getCityName() {
        return this.cityName;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", cityName=" + cityName + ", weatherDescription=" + weatherDescription + ", temperature=" + temperature.toString() + ",image=" + image + ",ident=" + cityIdentifier + '}';
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

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

}
