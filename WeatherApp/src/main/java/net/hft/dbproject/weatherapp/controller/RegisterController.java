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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.uiactions.Registerpageactions;

/**
 * FXML Controller class
 *
 * @author AVATSP
 */
public class RegisterController implements Initializable {
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField passwordField;
    
    @FXML
    private Pane registerPane;
    
    @FXML
    private Button registerButton;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Registerpageactions rpa = new Registerpageactions(this);
        registerButton.setOnAction(rpa.registerAction);
    }    

    public TextField getNameField() {
        return nameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }
    
    
    
    
}
