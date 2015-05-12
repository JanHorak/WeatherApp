package net.hft.dbproject.weatherapp.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
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
    private TextField nameField;

    @FXML
    private TextField zipField;

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
        new InetHeartBeat(inetConImage).startHeartBeat();
        this.propertiesService = new PropertiesService();

        initUIActions();

        WeatherAPIConnection weatherAPIConnection = new WeatherAPIConnection();
        List<WeatherInformation> weatherListByCityName = weatherAPIConnection.getWeatherListByCityName(this.propertiesService.getName());
        if (weatherListByCityName != null && weatherListByCityName.size() > 0)
        {
          currentWeather = weatherListByCityName.get(0);
        }
        else
        {
          currentWeather = new WeatherInformation();
        }
        processWeather(currentWeather);
        initUIInputs();
        LOGGER.info("Started completely. Current weather is loaded for: {}", currentWeather.getCityName());
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
        nameField.setText(currentWeather.getCityName());
        zipField.setText(currentWeather.getZipCode());
        if (propertiesService.getCalculation().equals("C")) {
            new Mainpageactions().cClick.handle(null);
            this.fahrenheit = false;
        } else {
            new Mainpageactions().fClick.handle(null);
            this.fahrenheit = true;
        }
        LOGGER.info("Preparing UI... done");
    }

    public TextField getNameField() {
        return this.nameField;
    }

    public TextField getZipField() {
        return this.zipField;
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
        if (!fahrenheit) {
            dMin = Utilities.toCelsius(weatherInformation.getTemperature().getMinTemp());
            dMax = Utilities.toCelsius(weatherInformation.getTemperature().getMaxTemp());
            dAvg = Utilities.toCelsius(weatherInformation.getTemperature().getAverageTemp());
        } else {
            dMin = weatherInformation.getTemperature().getMinTemp();
            dMax = weatherInformation.getTemperature().getMaxTemp();
            dAvg = weatherInformation.getTemperature().getAverageTemp();
        }
        maxTempValue.setText(String.valueOf(dMax));
        minTempValue.setText(String.valueOf(dMin));
        avgTempValue.setText(String.valueOf(dAvg));
        cityNameValue.setText(weatherInformation.getCityName());
    }


}
