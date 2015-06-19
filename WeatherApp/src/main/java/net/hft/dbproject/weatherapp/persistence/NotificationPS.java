/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.persistence;

import javax.persistence.NoResultException;
import net.hft.dbproject.weatherapp.entities.AppUser;
import net.hft.dbproject.weatherapp.entities.Notification;

/**
 *
 * @author Jan
 */
public class NotificationPS extends DataAccess {

    public Notification getNotificationInTagetlist(AppUser user, String cityName) {
        setup();
        openConnection();
        Notification n = null;
        try {
            n = (Notification) em.createNamedQuery("Notification.findNotificationsByUsernameAndCity").setParameter("name", user.getName()).setParameter("location", cityName).getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("No Notification found");
        }
        shutDown();
        return n;
    }

}
