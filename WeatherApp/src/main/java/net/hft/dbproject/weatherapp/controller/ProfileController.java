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
    private Pane pane; 

    @FXML
    private TextField oldpasswordField;
    @FXML
    private TextField newpasswordField;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ControllerContainer.addController(ProfileController.class, this);
        Profilepageactions spa = new Profilepageactions();
        this.saveButton.setOnAction(spa.saveAction);
        this.deleteButton.setOnAction(spa.deleteAction);
        pnameField.setText(LoggedInUser.getInstance().getName());
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

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
    
    
}
