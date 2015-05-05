/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
/**
 *
 * @author admin
 */
@Entity
public class Weather implements Serializable{
  
    private String cityName;
    private String weatherDescription;
    private Double temp;
    private Double minTemp;
    private Double maxTemp;
    private ArrayList<Temperature> tempArray;
    private Temperature cityTemp;
    
    
    public Weather(String cityName, String weatherDescription, Double temp, ArrayList<Temperature> tempArray)
    {
       this.cityName = cityName;
       this.weatherDescription = weatherDescription;
       this.temp = temp;
       this.tempArray = tempArray;
    }
    public Weather(String cityName)
    {
       this.cityName = cityName;
    }
    public Weather(String cityName, Temperature cityTemp)
    {
       this.cityName = cityName;
       this.temp = cityTemp.getTempFarenheit();
       this.minTemp = cityTemp.getMinTemp();
       this.maxTemp = cityTemp.getMaxTemp();
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
    
    public void setTemperature(Double ctemp) {
        this.temp = ctemp;
    }
    public Double getTemperature() {
        return this.temp;
    }
    
    public void setMinTemperature(Double minTemp) {
        this.minTemp = minTemp;
    }
    public Double getMinTemperature() {
        return this.minTemp;
    }
    public void setMaxTemperature(Double maxTemp) {
        this.maxTemp = maxTemp;
    }
    public Double getMaxTemperature() {
        return this.maxTemp;
    }
    
    public void setTempArray(ArrayList<Temperature> temp) {
        this.tempArray = temp;
    }
    public ArrayList<Temperature> getTempArray() {
        return this.tempArray;
    }

    
  @Override
    public String toString() {
        return new StringBuffer(" City Name : ").append(this.cityName)
               // .append(" Weather Description : ").append(this.weatherDescription)
               // .append(" Temperature : ").append(this.temp)
                .toString();
    }

    
}
