/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.uiactions;

import java.io.ByteArrayInputStream;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.hft.dbproject.weatherapp.controller.ComparecitiesController;
import net.hft.dbproject.weatherapp.entities.Location;
import net.hft.dbproject.weatherapp.helper.LoggedInUser;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.utilities.Utilities;

/**
 *
 * @author Jan
 */
public class Compactions {

    private final ComparecitiesController controller;

    public Compactions() {
        this.controller = (ComparecitiesController) ControllerContainer.getController(ComparecitiesController.class);
    }

    // TODO: Refactor and replace by Strat.- pattern!!
    public ChangeListener updateCityOne = new ChangeListener() {
        @Override
        public void changed(ObservableValue ov, Object t, Object t1) {

            Location loc = LoggedInUser.getInstance().getRequestedCityByName(cleanCityName((String) t1));
            if (loc.isDayTime()) {
                controller.getImageCityOne().setImage(new Image(new ByteArrayInputStream(loc.getImage().getImagedataDay())));
            } else {
                controller.getImageCityOne().setImage(new Image(new ByteArrayInputStream(loc.getImage().getImagedataNight())));
            }
            controller.getCelsiusCityOne().setText(String.valueOf(Math.round(Utilities.toCelsius(loc.getTemperature().getAverageTemp()))) + "°C");
            controller.getFahrenheitCityOne().setText(String.valueOf(Math.round(loc.getTemperature().getAverageTemp())) + "°F");
            controller.getDescCityOne().setText(loc.getWeatherDescription());
        }
    };

    public ChangeListener updateCityTwo = new ChangeListener() {
        @Override
        public void changed(ObservableValue ov, Object t, Object t1) {

            Location loc = LoggedInUser.getInstance().getRequestedCityByName(cleanCityName((String) t1));
            if (loc.isDayTime()) {
                controller.getImageCityTwo().setImage(new Image(new ByteArrayInputStream(loc.getImage().getImagedataDay())));
            } else {
                controller.getImageCityTwo().setImage(new Image(new ByteArrayInputStream(loc.getImage().getImagedataNight())));
            }
            controller.getCelsiusCityTwo().setText(String.valueOf(Math.round(Utilities.toCelsius(loc.getTemperature().getAverageTemp()))) + "°C");
            controller.getFahrenheitCityTwo().setText(String.valueOf(Math.round(loc.getTemperature().getAverageTemp())) + "°F");
            controller.getDescCityTwo().setText(loc.getWeatherDescription());
        }
    };

    public EventHandler close = new EventHandler() {

        @Override
        public void handle(Event t) {
            closeWindow();
        }
    };

    private void closeWindow() {
        Stage thisStage = (Stage) controller.getPane().getScene().getWindow();
        thisStage.close();
    }

    private String cleanCityName(String selectedValue) {
        return selectedValue.split("@")[0].trim();
    }

}
