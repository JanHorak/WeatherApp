/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author admin
 */
@Entity
public class WeatherInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String cityName;

    @NotNull
    private String weatherDescription;

    @NotNull
    @ManyToOne(targetEntity = Temperature.class)
//    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Temperature temperature;
    
    @NotNull
    private String zipCode;

    @NotNull
    @ManyToOne(targetEntity = WeatherImage.class)
//    @JoinColumn(name = "id", insertable = false, updatable = false)
    private WeatherImage image;

    public WeatherInformation(String cityName, String weatherDescription, Temperature temp) {
        this.cityName = cityName;
        this.weatherDescription = weatherDescription;
        this.temperature = temp;
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
        return new StringBuilder(" City Name : ").append(this.cityName)
                // .append(" Weather Description : ").append(this.weatherDescription)
                // .append(" Temperature : ").append(this.temp)
                .toString();
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    

}
