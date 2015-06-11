/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.persistence;

import java.util.List;
import net.hft.dbproject.weatherapp.entities.AppUser;
import net.hft.dbproject.weatherapp.entities.Notification;

/**
 *
 * @author Jan
 */
public interface AdminFunctions {

    public List<AppUser> findAllAppUsers();

    public List<Notification> findAllNotificationsByUsername(String userName);

    public void createNewNotification(AppUser user, String cityName, String email);
    
    public void deleteNotification(AppUser user, String cityName, String email);
    

}
