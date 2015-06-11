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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Jan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "AppStatistic.increaseNOS", query = "UPDATE AppStatistic a SET a.numberOfSaves = a.numberOfSaves + 1 WHERE a.id = 1")
})
public class AppStatistic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private int numberOfSaves;

    public int getNumberOfSaves() {
        return numberOfSaves;
    }

    public void setNumberOfSaves(int numberOfSaves) {
        this.numberOfSaves = numberOfSaves;
    }

}
