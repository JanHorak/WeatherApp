/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jan
 */
@Entity
@NamedQueries ({
    @NamedQuery(name = "WeatherImage.findByIconID", query = "SELECT i FROM WeatherImage i WHERE i.iconId = :iconId")
})
public class WeatherImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(columnDefinition = "blob")
    private byte[] imagedataDay;
    
    @NotNull
    @Column(columnDefinition = "blob")
    private byte[] imagedataNight;

    @NotNull
    @Column(unique = true)
    private int iconId;

    @OneToMany(mappedBy = "image", targetEntity = WeatherInformation.class)
    private List<WeatherInformation> info;

    public byte[] getImagedataDay() {
        return imagedataDay;
    }

    public void setImagedataDay(byte[] imagedataDay) {
        this.imagedataDay = imagedataDay;
    }

    public byte[] getImagedataNight() {
        return imagedataNight;
    }

    public void setImagedataNight(byte[] imagedataNight) {
        this.imagedataNight = imagedataNight;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<WeatherInformation> getInfo() {
        return info;
    }

    public void setInfo(List<WeatherInformation> info) {
        this.info = info;
    }

}
