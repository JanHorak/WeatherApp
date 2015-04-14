package net.hft.dbproject.weatherapp.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.uiactions.Mainpageactions;

public class MainpageController implements Initializable {

    @FXML
    private Pane mainpagePane;

    @FXML
    private ImageView exitImage;

    @FXML
    private ImageView moveImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initUIActions();
    }

    private void initUIActions() {

        // Loading actions
        Mainpageactions actions = new Mainpageactions();
        
        // Setting target- Pane
        actions.setPane(mainpagePane);
        
        // Setting moving location- action to image
        moveImage.setOnMouseClicked(actions.trackMousePosition);
        moveImage.setOnMouseDragged(actions.movePane);
        
        // Setting Exit- Action to exit- Icon
        exitImage.setOnMouseClicked(actions.exitEvent);
    }
}
