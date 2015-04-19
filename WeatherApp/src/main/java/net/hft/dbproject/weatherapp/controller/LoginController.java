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
import javafx.stage.Stage;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.uiactions.Mainpageactions;
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
    @FXML
    private Button cancelButton;
    private Stage stage;
        
     /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      initUIActions();   
    }    
    private void initUIActions()
    {
        // Loading actions
        Mainpageactions actions = new Mainpageactions();
        
    actions.setPane(loginPane);
    loginButton.setOnMouseClicked(actions.openStageAsRoot(stage,getClass().getResource("/fxml/mainpage/Scene.fxml"), CSSFile.CSS_DEFAULT, 251, 397));
    cancelButton.setOnMouseClicked(actions.exitEvent);
     }  
    
    
}
