/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.uiactions;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import net.hft.dbproject.weatherapp.controller.MainpageController;
import net.hft.dbproject.weatherapp.controller.SearchingDialogController;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.helper.DummyWeatherService;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.manager.Stagemanager;
import net.hft.dbproject.weatherapp.services.NotificationService;
import net.hft.dbproject.weatherapp.services.WeatherService;

/**
 *
 * @author Jan
 */
public class Searchpageactions {

    private SearchingDialogController controller;

    private WeatherService weatherService;

    public Searchpageactions() {
        this.controller = (SearchingDialogController) ControllerContainer.getController(SearchingDialogController.class);
        this.weatherService = new DummyWeatherService();
    }

    public EventHandler<ActionEvent> searchAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            String name = controller.getCityField().getText();
            String zip = controller.getZipField().getText();
            // Validation
            List<Control> controlsInError = new ArrayList<>();
            List<String> errorMessages = new ArrayList<>();
            boolean inError = false;
            int zipCode = 0;

            try {
                zipCode = Integer.decode(zip);
            } catch (NumberFormatException ex) {
                controlsInError.add(controller.getZipField());
                errorMessages.add("Zip code must be a number!");
                inError = true;
            }
            if (name.isEmpty() && zip.isEmpty()) {
                controlsInError.add(controller.getCityField());
                controlsInError.add(controller.getZipField());
                errorMessages.add("Name or Zipcode must be filled!");
                inError = true;
            }
            if (inError) {
                NotificationService.fireNotification(controlsInError, errorMessages);
                new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/dialogs/Notification.fxml"), CSSFile.CSS_TEST, 350, 100, false);
            } else {
                updateProgress(0.0);
                // Do the API- Request
                updateProgress(30.0);
                ObservableList<WeatherInformation> items = FXCollections.observableArrayList(weatherService.getWeatherListByCityName("Test"));

                controller.getWeatherList().setItems(items);
                updateProgress(100);

                NotificationService.resetErrorBorder();
            }

        }
    };

    private void updateProgress(double value) {
        controller.getIndicator().setProgress(value);
        controller.getProgressBar().setProgress(value);
    }

    public EventHandler<MouseEvent> handleSelection = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if (controller.getWeatherList().getSelectionModel().getSelectedIndex() >= 0) {
                controller.getApplyButton().setDisable(false);
                WeatherInformation wi = controller.getWeatherList().getSelectionModel().getSelectedItem();
                controller.setSelectedWeather(wi);

            } else {
                controller.getApplyButton().setDisable(true);
                controller.setSelectedWeather(new WeatherInformation());
            }
        }
    };

    public EventHandler<ActionEvent> applyWeatherSelection = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {
            MainpageController mpc = (MainpageController) ControllerContainer.getController(MainpageController.class);
            mpc.processWeather(controller.getSelectedWeather());
        }
    };

}
