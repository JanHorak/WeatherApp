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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author AVATSP
 */
public class ComparecitiesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView imageCityOne;
    @FXML
    private ImageView imageCityTwo;
    
    @FXML
    private ComboBox comboCityOne;
    @FXML
    private ComboBox comboCityTwo;

    @FXML
    private Label celsiusCityOne;
    @FXML
    private Label celsiusCityTwo;
    
    @FXML
    private Label fahrenheitCityOne;
    @FXML
    private Label fahrenheitCityTwo;
    
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
