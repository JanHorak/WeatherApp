/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jan
 */
@Entity
public class Temperature implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private double averageTemp;

    @NotNull
    private double minTemp;

    @NotNull
    private double maxTemp;

    @NotNull
    @OneToMany(mappedBy = "temperature", targetEntity = WeatherInformation.class)
    private List<WeatherInformation> information;

    public Temperature(double averageTemp, double minTemp, double maxTemp) {
        this.averageTemp = averageTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
    
    public double getAverageTemp() {
        return averageTemp;
    }

    public void setAverageTemp(double averageTemp) {
        this.averageTemp = averageTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<WeatherInformation> getInformation() {
        return information;
    }

    public void setInformation(List<WeatherInformation> information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "Temperature{" + "averageTemp=" + averageTemp + ", minTemp=" + minTemp + ", maxTemp=" + maxTemp + '}';
    }

}
