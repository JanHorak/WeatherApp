    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.controller;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.entities.Location;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.persistence.LocationBaseService;
import net.hft.dbproject.weatherapp.persistence.LocationPersistenceService;
import net.hft.dbproject.weatherapp.uiactions.Dashboardactions;
import net.hft.dbproject.weatherapp.utilities.Utilities;

/**
 * FXML Controller class
 *
 * @author AVATSP
 */
public class DashboardController implements Initializable {

    @FXML
    private Label dayone;
    @FXML
    private Label daytwo;
    @FXML
    private Label daythree;
    @FXML
    private Label dayonecelcius;
    @FXML
    private Label daytwocelcius;
    @FXML
    private Label daythreecelcius;
    @FXML
    private Label dayonefahr;
    @FXML
    private Label daytwofahr;
    @FXML
    private Label daythreefahr;
    @FXML
    private Label dayonetime;
    @FXML
    private Label daytwotime;
    @FXML
    private Label daythreetime;
    @FXML
    private ImageView imageViewOne;
    @FXML
    private ImageView imageViewTwo;
    @FXML
    private ImageView imageViewThree;
    @FXML
    private Button profileButton;
    @FXML
    private Button compareButton;
    @FXML
    private Pane dashboardPane;
    @FXML
    private ComboBox cityCombobox;
    @FXML
    private Button logoutButton;

    private DashboardController controller;

    private LocationBaseService locationService = null;
    
    @FXML
    private Pane pane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ControllerContainer.addController(DashboardController.class, this);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initUIActions();
                locationService = new LocationPersistenceService();

                List<Location> history = locationService.getFirstThreeLocations();
                selectCitylist();
                updateHistory(history);
            }

        });

    }

    private void initUIActions() {
        Dashboardactions actions = new Dashboardactions();
        profileButton.setOnAction(actions.openProfilePage);
        compareButton.setOnAction(actions.openComparePage);
        cityCombobox.valueProperty().addListener(actions.cityListSelection);
        logoutButton.setOnAction(actions.logout);

    }

    public void updateHistory(List<Location> history) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy \n HH:mm");
        String notAvailableText = "n/a";

        // Day 1
        dayone.setText(history.get(0).getCityName());
        imageViewOne.setImage(new Image(new ByteArrayInputStream(history.get(0).getImage().getImagedataDay())));
        dayonecelcius.setText(String.valueOf(Math.round(Utilities.toCelsius(history.get(0).getTemperature().getAverageTemp()))) + "°C");
        dayonefahr.setText(String.valueOf(Math.round(history.get(0).getTemperature().getAverageTemp())) + "°F");
        dayonetime.setText(sdf.format(history.get(0).getRequestTime()));
        // Day 2
        if (history.size() >= 2) {
            daytwo.setText(history.get(1).getCityName());
            imageViewTwo.setImage(new Image(new ByteArrayInputStream(history.get(1).getImage().getImagedataDay())));
            daytwocelcius.setText(String.valueOf(Math.round(Utilities.toCelsius(history.get(1).getTemperature().getAverageTemp()))) + "°C");
            daytwofahr.setText(String.valueOf(Math.round(history.get(1).getTemperature().getAverageTemp())) + "°F");
            daytwotime.setText(sdf.format(history.get(1).getRequestTime()));
        } else {
            daytwo.setText(notAvailableText);
            imageViewTwo.setImage(new Image(new ByteArrayInputStream(history.get(0).getImage().getImagedataDay())));
            daytwocelcius.setText(notAvailableText);
            daytwofahr.setText(notAvailableText);
            daytwotime.setText(notAvailableText);
        }
        // Day 3
        if (history.size() == 3) {
            daythree.setText(history.get(2).getCityName());
            imageViewThree.setImage(new Image(new ByteArrayInputStream(history.get(2).getImage().getImagedataDay())));
            daythreecelcius.setText(String.valueOf(Math.round(Utilities.toCelsius(history.get(2).getTemperature().getAverageTemp()))) + "°C");
            daythreefahr.setText(String.valueOf(Math.round(history.get(2).getTemperature().getAverageTemp())) + "°F");
            daythreetime.setText(sdf.format(history.get(2).getRequestTime()));
        } else {
            daythree.setText(notAvailableText);
            imageViewThree.setImage(new Image(new ByteArrayInputStream(history.get(0).getImage().getImagedataDay())));
            daythreecelcius.setText(notAvailableText);
            daythreefahr.setText(notAvailableText);
            daythreetime.setText(notAvailableText);
        }

    }

    public void selectCitylist() {
        // Cleaning up
        cityCombobox.setItems(null);

        // Filling List
        List<Location> allWi = locationService.findAll();
        List<String> inputs = new ArrayList<>();

        for (Location i : allWi) {
            if (!inputs.contains(i.getCityName())) {
                inputs.add(i.getCityName());
            }
        }

        ObservableList<String> optionsForComboList
                = FXCollections.observableArrayList(inputs);

        cityCombobox.setItems(optionsForComboList);
    }

    public Label getDayOneFahr() {
        return dayonefahr;
    }

    public void setDayOneFahr(Label dayonefahr) {
        this.dayonefahr = dayonefahr;
    }

    public Label getDayTwoFahr() {
        return daytwofahr;
    }

    public void setDayTwoFahr(Label daytwofahr) {
        this.daytwofahr = daytwofahr;
    }

    public Label getDayThreeFahr() {
        return daythreefahr;
    }

    public void setDayThreeFahr(Label daythreefahr) {
        this.daythreefahr = daythreefahr;
    }

    public Label getDayOneCelcius() {
        return dayonecelcius;
    }

    public void setDayOneCelcius(Label dayonecelcius) {
        this.dayonecelcius = dayonecelcius;
    }

    public Label getDayTwoCelcius() {
        return daytwocelcius;
    }

    public void setDayTwoCelcius(Label daytwocelcius) {
        this.daytwocelcius = daytwocelcius;
    }

    public Label getDayThreeCelcius() {
        return daythreecelcius;
    }

    public void setDayThreeCelcius(Label daythreecelcius) {
        this.daythreecelcius = daythreecelcius;
    }

    public Pane getPane() {
        return pane;
    }
    
    
}
