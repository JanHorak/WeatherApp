/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.entities;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
/**
 *
 * @author admin
 */
public class Weather implements Serializable{
  
    private String cityName;
    private String weatherDescription;
    private String temp;
    
    
    
    public Weather(String cityName, String weatherDescription, String temp)
    {
       this.cityName = cityName;
       this.weatherDescription = weatherDescription;
       this.temp = temp;
    }
    public Weather(String cityName)
    {
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
    
    public void setTemperature(String ctemp) {
        this.temp = ctemp;
    }
    public String getTemperature() {
        return this.temp;
    }

    
  @Override
    public String toString() {
        return new StringBuffer(" City Name : ").append(this.cityName)
               // .append(" Weather Description : ").append(this.weatherDescription)
               // .append(" Temperature : ").append(this.temp)
                .toString();
    }

    
}
