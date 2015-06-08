package net.hft.dbproject.weatherapp.controller;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.entities.WeatherImage;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.persistence.WeatherPersistenceService;
import net.hft.dbproject.weatherapp.services.InetHeartBeat;
import net.hft.dbproject.weatherapp.services.PropertiesService;
import net.hft.dbproject.weatherapp.services.WeatherAPIConnection;
import net.hft.dbproject.weatherapp.uiactions.Mainpageactions;
import net.hft.dbproject.weatherapp.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainpageController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainpageController.class);

    private PropertiesService propertiesService;

    private boolean fahrenheit;

    @FXML
    private Pane mainpagePane;

    @FXML
    private ImageView exitImage;

    @FXML
    private ImageView moveImage;

    @FXML
    private ImageView inetConImage;

    @FXML
    private ImageView cImage;

    @FXML
    private ImageView fImage;

    @FXML
    private Button searchButton;

    @FXML
    private Hyperlink signinLink;

    @FXML
    private Hyperlink registerLink;

    @FXML
    private ImageView weatherImage;

    @FXML
    private Label maxTempValue;

    @FXML
    private Label minTempValue;

    @FXML
    private Label avgTempValue;

    @FXML
    private Label cityNameValue;

    private WeatherInformation currentWeather;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ControllerContainer.addController(MainpageController.class, this);
        currentWeather = new WeatherInformation();
        new InetHeartBeat(inetConImage).startHeartBeat();
        propertiesService = new PropertiesService();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initUIActions();
                initUIInputs();
                currentWeather = WeatherAPIConnection.requestCityByID(Integer.valueOf(propertiesService.getIdentCode()));
                WeatherImage i = new WeatherImage();
                WeatherPersistenceService p = new WeatherPersistenceService();
                i = p.getImageByIconID(currentWeather.getImage().getIconId());
                currentWeather.setImage(i);
                processWeather(currentWeather);
            }
        });

    }

    private void initUIActions() {
        // Loading actions
        Mainpageactions actions = new Mainpageactions();

        // Setting moving location- action to image
        moveImage.setOnMouseClicked(actions.trackMousePosition);
        moveImage.setOnMouseDragged(actions.movePane);
        fImage.setOnMouseClicked(actions.fClickSave);
        cImage.setOnMouseClicked(actions.cClickSave);

        // Setting Exit- Action to exit- Icon
        exitImage.setOnMouseClicked(actions.exitEvent);

        // Buttonactions
        searchButton.setGraphic(new ImageView(getClass().getResource("/images/UI/search.png").toString()));
        searchButton.setOnAction(actions.openSearchDialog);

        // Links
        registerLink.setOnAction(actions.openRegisterPage);
        signinLink.setOnAction(actions.openLoginPage);

        LOGGER.info("Loading UI- Actions... done");
    }

    private void initUIInputs() {
        if (propertiesService.getCalculation().equals("C")) {
            new Mainpageactions().cClick.handle(null);
            this.fahrenheit = false;
        } else {
            new Mainpageactions().fClick.handle(null);
            this.fahrenheit = true;
        }
        System.out.println(propertiesService.getIdentCode());
        LOGGER.info("Preparing UI... done");
    }

    public void setCurrentWeather(WeatherInformation currentWeather) {
        this.currentWeather = currentWeather;
    }

    public ImageView getcImage() {
        return cImage;
    }

    public ImageView getfImage() {
        return fImage;
    }

    public Pane getMainpagePane() {
        return mainpagePane;
    }

    public Label getMaxTempValue() {
        return maxTempValue;
    }

    public void setMaxTempValue(Label maxTempValue) {
        this.maxTempValue = maxTempValue;
    }

    public Label getMinTempValue() {
        return minTempValue;
    }

    public void setMinTempValue(Label minTempValue) {
        this.minTempValue = minTempValue;
    }

    public Label getAvgTempValue() {
        return avgTempValue;
    }

    public void setAvgTempValue(Label avgTempValue) {
        this.avgTempValue = avgTempValue;
    }

    public void processWeather(WeatherInformation weatherInformation) {
        double dMin;
        double dMax;
        double dAvg;
        String suffix = "";
        if (!fahrenheit) {
            suffix = "°C";
            dMin = Utilities.toCelsius(weatherInformation.getTemperature().getMinTemp());
            dMax = Utilities.toCelsius(weatherInformation.getTemperature().getMaxTemp());
            dAvg = Utilities.toCelsius(weatherInformation.getTemperature().getAverageTemp());
        } else {
            suffix = "°F";
            System.out.println(weatherInformation.toString());
            dMin = weatherInformation.getTemperature().getMinTemp();
            dMax = weatherInformation.getTemperature().getMaxTemp();
            dAvg = weatherInformation.getTemperature().getAverageTemp();
        }
        maxTempValue.setText(String.valueOf(dMax).concat(suffix));
        minTempValue.setText(String.valueOf(dMin).concat(suffix));
        avgTempValue.setText(String.valueOf(dAvg).concat(suffix));
        weatherImage.setImage(new Image(new ByteArrayInputStream(weatherInformation.getImage().getImagedataDay())));
        cityNameValue.setText(weatherInformation.getCityName());
    }

}
