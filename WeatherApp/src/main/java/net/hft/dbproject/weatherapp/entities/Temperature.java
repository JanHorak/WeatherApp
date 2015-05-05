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
import javax.persistence.MappedSuperclass;
/**
 *
 * @author admin
 */
public class Temperature {
    
    private Double tempFarenheit;
    private Double maxTemp;
    private Double minTemp;
    
    public Temperature(Double tempFarenheit, Double maxTemp, Double minTemp)
    {
       this.tempFarenheit = tempFarenheit;
       this.maxTemp = maxTemp;
       this.minTemp = minTemp;
    }
    
    public void setTempFarenheit(Double tempFarenheit) {
        this.tempFarenheit = tempFarenheit;
    }
    public Double getTempFarenheit() {
        return this.tempFarenheit;
    }
    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }
    public Double getMaxTemp() {
        return this.maxTemp;
    }
    
    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }
    public Double getMinTemp() {
        return this.minTemp;
    }
    
    @Override
    public String toString() {
        return new StringBuffer(" Temperature in Farenheit : ").append(this.tempFarenheit)
                .append(" Max Temperature : ").append(this.maxTemp)
                .append(" Min Temperature : ").append(this.minTemp)
                .toString();
    }
}
