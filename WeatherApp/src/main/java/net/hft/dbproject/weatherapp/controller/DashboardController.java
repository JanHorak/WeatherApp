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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.uiactions.Dashboardactions;

/**
 * FXML Controller class
 *
 * @author AVATSP
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane anchor;
   
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
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ControllerContainer.addController(DashboardController.class, this);
        
        initUIActions();
    }

    private void initUIActions() {
        Dashboardactions actions = new Dashboardactions();
        
        profileButton.setOnAction(actions.openProfilePage);
        compareButton.setOnAction(actions.openComparePage);
    }

}
