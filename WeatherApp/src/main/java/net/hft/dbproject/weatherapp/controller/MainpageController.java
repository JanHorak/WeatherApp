package net.hft.dbproject.weatherapp.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.services.InetHeartBeat;
import net.hft.dbproject.weatherapp.services.PropertiesService;
import net.hft.dbproject.weatherapp.uiactions.Mainpageactions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainpageController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainpageController.class);

    private PropertiesService propertiesService;

    @FXML
    private Pane mainpagePane;

    @FXML
    private ImageView exitImage;

    @FXML
    private ImageView moveImage;

    @FXML
    private ImageView inetConImage;

    @FXML
    private TextField nameField;

    @FXML
    private TextField zipField;

    @FXML
    private Button searchButton;

    @FXML
    private Hyperlink signinLink;

    @FXML
    private Hyperlink registerLink;

    private InetHeartBeat heartBeat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new InetHeartBeat(inetConImage).startHeartBeat();

        this.propertiesService = new PropertiesService();
        loadIniData();
        initUIActions();
        LOGGER.info("Started completely");
    }

    private void initUIActions() {
        LOGGER.info("Loading UI- Actions...");
        // Loading actions
        Mainpageactions actions = new Mainpageactions(this);

        // Setting target- Pane
        actions.setPane(mainpagePane);

        // Setting moving location- action to image
        moveImage.setOnMouseClicked(actions.trackMousePosition);
        moveImage.setOnMouseDragged(actions.movePane);

        // Setting Exit- Action to exit- Icon
        exitImage.setOnMouseClicked(actions.exitEvent);

        // Buttonactions
        searchButton.setOnAction(actions.searchAction);
        LOGGER.info("Loading UI- Actions... done");
        registerLink.setOnAction(actions.openRegisterPage);
        signinLink.setOnAction(actions.openLoginPage);
    }

    private void loadIniData() {
        nameField.setText(propertiesService.getName());
        zipField.setText(propertiesService.getZipCode());
    }

    public TextField getNameField() {
        return this.nameField;
    }

    public TextField getZipField() {
        return this.zipField;
    }
}
