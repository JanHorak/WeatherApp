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
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;

/**
 * FXML Controller class
 *
 * @author AVATSP
 */
public class LoginController implements Initializable {
    
    @FXML
    private Pane loginPane;
    @FXML
    private Button loginButton;
        
     /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        ControllerContainer.addController(LoginController.class, this);
    }    
    
}
