package net.hft.dbproject.weatherapp.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.services.InetHeartBeat;
import net.hft.dbproject.weatherapp.uiactions.Mainpageactions;
import org.apache.log4j.Logger;

public class MainpageController implements Initializable {
    
    private Logger logger;

    @FXML
    private Pane mainpagePane;

    @FXML
    private ImageView exitImage;

    @FXML
    private ImageView moveImage;
    
    @FXML
    private ImageView inetConImage;
    
    private InetHeartBeat heartBeat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.logger = Logger.getLogger(MainpageController.class);
        this.heartBeat = new InetHeartBeat(inetConImage);
        this.heartBeat.start();
        initUIActions();
        this.logger.info("Started completely");
    }

    private void initUIActions() {
        this.logger.info("Loading UI- Actions...");
        // Loading actions
        Mainpageactions actions = new Mainpageactions();
        
        // Setting target- Pane
        actions.setPane(mainpagePane);
        
        // Setting moving location- action to image
        moveImage.setOnMouseClicked(actions.trackMousePosition);
        moveImage.setOnMouseDragged(actions.movePane);
        
        // Setting Exit- Action to exit- Icon
        exitImage.setOnMouseClicked(actions.exitEvent);
        this.logger.info("Loading UI- Actions... done");
    }
}
