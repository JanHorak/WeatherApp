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
public class AdminManager extends DataAccess implements AdminFunctions {

    @Override
    public List<AppUser> findAllAppUsers() {
        setup();
        openConnection();
        List<AppUser> appusers = (List<AppUser>) em.createNamedQuery("AppUser.findAll").getResultList();
        shutDown();
        return appusers;
    }

    @Override
    public List<Notification> findAllNotificationsByUsername(String userName) {
        setup();
        openConnection();
        List<Notification> notificationsFromUser = (List<Notification>) em.createNamedQuery("Notification.findAllByUserName").setParameter("username", userName).getResultList();
        shutDown();
        return notificationsFromUser;
    }

    @Override
    public void createNewNotification(AppUser user, String cityName, String email) {
        setup();
        openConnection();
        Notification notification = new Notification();
        notification.setTarget(user);
        notification.setTargetLocation(cityName);
        notification.setEmailAddress(email);
        em.persist(notification);
        em.getTransaction().commit();
        shutDown();
    }

    @Override
    public void deleteNotification(AppUser user, String cityName, String email) {
        setup();
        openConnection();
        Notification notification = new Notification();
        notification.setTarget(user);
        notification.setTargetLocation(cityName);
        notification.setEmailAddress(email);
        em.remove(notification);
        em.getTransaction().commit();
        shutDown();
        
    }

}
