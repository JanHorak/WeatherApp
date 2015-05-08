/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.uiactions.Searchpageactions;

/**
 * FXML Controller class
 *
 * @author Jan
 */
public class SearchingDialogController implements Initializable {

    @FXML
    private TextField cityField;

    @FXML
    private TextField zipField;

    @FXML
    private Button searchButton;

    @FXML
    private Button applyButton;

    @FXML
    private ListView<WeatherInformation> weatherList;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ProgressIndicator indicator;

    private WeatherInformation selectedWeather;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ControllerContainer.addController(SearchingDialogController.class, this);
        this.selectedWeather = new WeatherInformation();
        initActions();
    }

    private void initActions() {
        Searchpageactions actions = new Searchpageactions();
        this.searchButton.setOnAction(actions.searchAction);
        this.weatherList.setOnMouseClicked(actions.handleSelection);
        this.applyButton.setOnAction(actions.applyWeatherSelection);
    }

    public TextField getCityField() {
        return cityField;
    }

    public TextField getZipField() {
        return zipField;
    }

    public ListView<WeatherInformation> getWeatherList() {
        return weatherList;
    }

    public Button getApplyButton() {
        return applyButton;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public ProgressIndicator getIndicator() {
        return indicator;
    }

    public WeatherInformation getSelectedWeather() {
        return selectedWeather;
    }

    public void setSelectedWeather(WeatherInformation selectedWeather) {
        this.selectedWeather = selectedWeather;
    }

    
    
}
