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
import javafx.scene.control.TextField;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public TextField getCityField() {
        return cityField;
    }

    public TextField getZipField() {
        return zipField;
    }
   
}
