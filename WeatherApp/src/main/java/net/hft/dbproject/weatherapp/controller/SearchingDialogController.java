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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.helper.JSONConvertObject;
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
    private RadioButton offline;

    @FXML
    private RadioButton online;

    @FXML
    private Button searchButton;

    @FXML
    private Button applyButton;

    @FXML
    private ListView<JSONConvertObject> locationList;
    
    @FXML
    private Button previewButton;

    @FXML
    private ProgressBar progressBar;
    
    @FXML
    private Pane pane;

    private JSONConvertObject selectedLocation;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ControllerContainer.addController(SearchingDialogController.class, this);
        this.selectedLocation = new JSONConvertObject();
        initActions();
    }

    private void initActions() {
        Searchpageactions actions = new Searchpageactions();
        this.searchButton.setOnAction(actions.searchAction);
        this.locationList.setOnMouseClicked(actions.handleSelection);
        this.applyButton.setOnAction(actions.applyWeatherSelection);
        this.online.setOnAction(actions.doClickOnline);
        this.offline.setOnAction(actions.doClickOffline);
        this.previewButton.setOnAction(actions.showPreview);
    }

    public TextField getCityField() {
        return cityField;
    }

    public ListView<JSONConvertObject> getWeatherList() {
        return locationList;
    }

    public Button getApplyButton() {
        return applyButton;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public JSONConvertObject getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(JSONConvertObject selectedLocation) {
        this.selectedLocation = selectedLocation;
    }

    public RadioButton getOffline() {
        return offline;
    }

    public void setOffline(RadioButton offline) {
        this.offline = offline;
    }

    public RadioButton getOnline() {
        return online;
    }

    public void setOnline(RadioButton online) {
        this.online = online;
    }

    public Pane getPane() {
        return pane;
    }

    public Button getPreviewButton() {
        return previewButton;
    }

    
    
}
