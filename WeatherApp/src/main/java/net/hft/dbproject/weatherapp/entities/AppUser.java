/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Jan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "AppUser.findByID", query = "SELECT u FROM AppUser u WHERE u.id = :id"),
    @NamedQuery(name = "AppUser.fingByNAME", query = "SELECT u FROM AppUser u WHERE u.name =:name"),
    @NamedQuery(name = "AppUser.findAll", query = "SELECT u FROM AppUser u"),
    @NamedQuery(name = "AppUser.updateByPASSWORD", query = "UPDATE AppUser u SET u.password = :newPassword WHERE u.id = :id")})
public class AppUser extends UserBase implements Serializable {

    @OneToMany(mappedBy = "target", targetEntity = Notification.class,
                cascade = CascadeType.ALL)
    private List<Notification> notifications;
    
    @OneToMany(mappedBy = "searcher", targetEntity = Location.class,
                cascade = CascadeType.ALL)
    private List<Location> searchedLocation;

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
    
    public List<Location> getSearchedWeather() {
        return searchedLocation;
    }

    public void setSearchedWeather(List<Location> searchedWeather) {
        this.searchedLocation = searchedWeather;
    }

    @Override
    public String toString() {
        return super.getName();
    }
    
}
