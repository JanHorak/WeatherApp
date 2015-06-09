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
import net.hft.dbproject.weatherapp.entities.AppUser;
import net.hft.dbproject.weatherapp.helper.LoggedInUser;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.uiactions.Profilepageactions;

/**
 * FXML Controller class
 *
 * @author AVATSP
 */
public class ProfileController implements Initializable {

    @FXML
    private TextField pnameField;

    @FXML
    private TextField oldpasswordField;
    @FXML
    private TextField newpasswordField;
    @FXML
    private Button saveButton;
    
    private AppUser currentUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ControllerContainer.addController(ProfileController.class, this);
        Profilepageactions spa = new Profilepageactions();
        saveButton.setOnAction(spa.saveAction);
        currentUser = LoggedInUser.getInstance();
        pnameField.setText(currentUser.getName());
    }

    public TextField getPnameField() {
        return pnameField;
    }

    public TextField getOldpasswordField() {
        return oldpasswordField;
    }

    public TextField getNewpasswordField() {
        return newpasswordField;
    }
}
