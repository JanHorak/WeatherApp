/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.entities.AppUser;
import net.hft.dbproject.weatherapp.entities.Location;
import net.hft.dbproject.weatherapp.helper.LoggedInUser;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.uiactions.Compactions;

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
    private Pane pane;

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
    private Label descCityOne;
    @FXML
    private Label descCityTwo;

    @FXML
    private Button backButton;

    private AppUser currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ControllerContainer.addController(ComparecitiesController.class, this);

        loadCitylist();
        Compactions actions = new Compactions();
        comboCityOne.valueProperty().addListener(actions.updateCityOne);
        comboCityTwo.valueProperty().addListener(actions.updateCityTwo);
        backButton.setOnAction(actions.close);
    }

    public void loadCitylist() {
        // Cleaning up
        comboCityOne.setItems(null);
        comboCityTwo.setItems(null);
        currentUser = LoggedInUser.getInstance();
        // Filling List
        List<Location> allWi = currentUser.getSearchedWeather();
        List<String> inputs = new ArrayList<>();

        for (Location i : allWi) {
            if (!inputs.contains(i.getCityName())) {
                inputs.add(i.getCityName() + " @ " + i.getRequestTime().toString());
            }
        }

        ObservableList<String> optionsForComboList
                = FXCollections.observableArrayList(inputs);

        comboCityOne.setItems(optionsForComboList);
        comboCityTwo.setItems(optionsForComboList);
    }

    public ImageView getImageCityOne() {
        return imageCityOne;
    }

    public ImageView getImageCityTwo() {
        return imageCityTwo;
    }

    public ComboBox getComboCityOne() {
        return comboCityOne;
    }

    public ComboBox getComboCityTwo() {
        return comboCityTwo;
    }

    public Label getCelsiusCityOne() {
        return celsiusCityOne;
    }

    public Label getCelsiusCityTwo() {
        return celsiusCityTwo;
    }

    public Label getFahrenheitCityOne() {
        return fahrenheitCityOne;
    }

    public Label getFahrenheitCityTwo() {
        return fahrenheitCityTwo;
    }

    public Label getDescCityOne() {
        return descCityOne;
    }

    public Label getDescCityTwo() {
        return descCityTwo;
    }

    public Pane getPane() {
        return pane;
    }

}
