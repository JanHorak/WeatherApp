/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.uiactions;

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import net.hft.dbproject.weatherapp.controller.AdminController;
import net.hft.dbproject.weatherapp.entities.AppUser;
import net.hft.dbproject.weatherapp.entities.Notification;
import net.hft.dbproject.weatherapp.entities.Location;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.helper.LoggedInUser;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.manager.Stagemanager;
import net.hft.dbproject.weatherapp.persistence.AdminFunctions;
import net.hft.dbproject.weatherapp.persistence.AdminManager;

/**
 *
 * @author Jan
 */
public class Adminpageactions {

    private final AdminController controller;

    private AdminFunctions adminManager = new AdminManager();

    public Adminpageactions() {
        this.controller = (AdminController) ControllerContainer.getController(AdminController.class);
    }

    public ChangeListener userListSelection = new ChangeListener() {
        @Override
        public void changed(ObservableValue ov, Object t, Object t1) {
            controller.setSelectedUser((AppUser) t1);
            List<Location> history = ((AppUser) t1).getSearchedWeather();
            List<Notification> notList = controller.getSelectedUser().getNotifications();
            controller.updateHistory(history);
            controller.updateNotifications(notList);
        }
    };

    public ChangeListener notificationListSelection = new ChangeListener() {
        @Override
        public void changed(ObservableValue ov, Object t, Object t1) {

        }
    };

    // @TODO: REFRESHING 
    public EventHandler<ActionEvent> saveNotificationEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            String cityName = controller.getCityField().getText();
            String mailAddress = controller.getMailAddressField().getText();
            AppUser target = controller.getSelectedUser();
            adminManager.createNewNotification(target, cityName, mailAddress);
            controller.getCityField().setText("");
            controller.getMailAddressField().setText("");

        }
    };

    public EventHandler<ActionEvent> deleteNotificationEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            String cityName = controller.getSelectedNotification().split(", ")[0];
            String mail = controller.getSelectedNotification().split(", ")[1];
            AppUser target = controller.getSelectedUser();
            adminManager.deleteNotification(target, cityName, mail);
        }
    };

}
