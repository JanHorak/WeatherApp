package net.hft.dbproject.weatherapp.uiactions;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.hft.dbproject.weatherapp.controller.DashboardController;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.manager.Stagemanager;
import net.hft.dbproject.weatherapp.persistence.LocationBaseService;
import net.hft.dbproject.weatherapp.persistence.LocationPersistenceService;
import net.hft.dbproject.weatherapp.services.PropertiesService;

/**
 *
 * @author AVATSP
 */
public class Dashboardactions {

    DashboardController controller;
    PropertiesService propertiesService;

    public Dashboardactions() {

        this.controller = (DashboardController) ControllerContainer.getController(DashboardController.class);
        this.propertiesService = new PropertiesService();

    }

    public EventHandler<ActionEvent> openProfilePage = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/mainpage/Profile.fxml"), CSSFile.CSS_DEFAULT, 251, 397, true);
        }
    };

    public EventHandler<ActionEvent> openComparePage = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/mainpage/Comparecities.fxml"), CSSFile.CSS_DEFAULT, 350, 397, true);
        }

    };

    public ChangeListener cityListSelection = new ChangeListener() {
        @Override
        public void changed(ObservableValue ov, Object t, Object t1) {
            LocationBaseService service = new LocationPersistenceService();
            controller.updateHistory(service.getThreeLocationsByName((String) t1));
        }
    };
};
