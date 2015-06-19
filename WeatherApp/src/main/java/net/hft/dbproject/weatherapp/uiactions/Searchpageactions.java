/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.uiactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.hft.dbproject.weatherapp.controller.MainpageController;
import net.hft.dbproject.weatherapp.controller.SearchingDialogController;
import net.hft.dbproject.weatherapp.entities.WeatherImage;
import net.hft.dbproject.weatherapp.entities.Location;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.helper.JSONConvertObject;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.manager.Stagemanager;
import net.hft.dbproject.weatherapp.persistence.LocationBaseService;
import net.hft.dbproject.weatherapp.persistence.LocationPersistenceService;
import net.hft.dbproject.weatherapp.services.Locationfinder;
import net.hft.dbproject.weatherapp.services.NotificationService;
import net.hft.dbproject.weatherapp.services.OfflineLocationFinder;
import net.hft.dbproject.weatherapp.services.OnlineLocationFinder;
import net.hft.dbproject.weatherapp.services.PropertiesService;
import net.hft.dbproject.weatherapp.services.WeatherAPIConnection;
import net.hft.dbproject.weatherapp.services.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jan
 */
public class Searchpageactions {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Searchpageactions.class);

    private SearchingDialogController controller;

    private LocationBaseService weatherPersistenceService;

    private Locationfinder locationfinder;

    public Searchpageactions() {
        this.controller = (SearchingDialogController) ControllerContainer.getController(SearchingDialogController.class);
    }

    public EventHandler<ActionEvent> searchAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            updateProgress(-1);
            controller.getWeatherList().setItems(null);
            String name = controller.getCityField().getText();

            // Validation
            List<Control> controlsInError = new ArrayList<>();
            List<String> errorMessages = new ArrayList<>();
            boolean inError = false;

            if (name.isEmpty()) {
                controlsInError.add(controller.getCityField());
                errorMessages.add("Name must be filled!");
                inError = true;
            }
            if (inError) {
                updateProgress(100);
                NotificationService.fireNotification(controlsInError, errorMessages);
                new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/dialogs/Notification.fxml"), CSSFile.CSS_DEFAULT, 350, 100, false);
            } else {
                // Do the API- Request
                if (controller.getOffline().isSelected()) {
                    locationfinder = new OfflineLocationFinder();
                } else {
                    locationfinder = new OnlineLocationFinder();
                }
                ObservableList<JSONConvertObject> items = FXCollections.observableArrayList(locationfinder.getLocationsByCityName(name));

                controller.getWeatherList().setItems(items);
                updateProgress(100);

                NotificationService.resetErrorBorder();
            }

        }
    };

    private void closeWindow() {
        Stage thisStage = (Stage) controller.getPane().getScene().getWindow();
        thisStage.close();
    }

    private void updateProgress(double value) {
        controller.getProgressBar().setProgress(value);
    }

    public EventHandler<MouseEvent> handleSelection = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if (controller.getWeatherList().getSelectionModel().getSelectedIndex() >= 0) {
                controller.getApplyButton().setDisable(false);
                JSONConvertObject wi = controller.getWeatherList().getSelectionModel().getSelectedItem();
                System.out.println(wi.getId());
                controller.setSelectedLocation(wi);
            } else {
                controller.getApplyButton().setDisable(true);
                controller.setSelectedLocation(new JSONConvertObject());
            }
        }
    };

    public EventHandler<ActionEvent> applyWeatherSelection = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {
            MainpageController mpc = (MainpageController) ControllerContainer.getController(MainpageController.class);
            weatherPersistenceService = new LocationPersistenceService();
            Location wi = null;
            if (controller.getOnline().isSelected()) {
                wi = OnlineLocationFinder.getWeatherFromCacheById(controller.getSelectedLocation().getId());
                LOGGER.debug("Online detected");
                LOGGER.debug("Got weather: {}", wi.toString());
            } else {
                // Replacing the stored Image (dummy) with a real from the database
                LOGGER.debug("Offline detected");
                wi = WeatherAPIConnection.requestCityByID(controller.getSelectedLocation().getId());
                LOGGER.debug("Got weather: {}", wi.toString() );
            }

            WeatherImage image = weatherPersistenceService.getImageByIconID(wi.getImage().getIconId());
            wi.setImage(image);
            wi.setRequestTime(new Date());
            mpc.processWeather(wi);
            PropertiesService propertiesService = new PropertiesService();
            propertiesService.storeCityAndId(wi.getCityName(), wi.getCityIdentifier());
            closeWindow();
        }
    };

    public EventHandler<ActionEvent> doClickOnline = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {
            controller.getOffline().setSelected(false);
        }
    };

    public EventHandler<ActionEvent> doClickOffline = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {
            controller.getOnline().setSelected(false);
        }
    };

}
