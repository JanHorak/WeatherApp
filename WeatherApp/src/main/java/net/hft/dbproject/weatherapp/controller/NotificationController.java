package net.hft.dbproject.weatherapp.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.services.NotificationService;
import net.hft.dbproject.weatherapp.uiactions.Notificationactions;

/**
 *
 * @author Jan
 */
public class NotificationController implements Initializable {

    @FXML
    private Label errorLabel;
    
    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ControllerContainer.addController(NotificationController.class, this);
        setErrorMessage(NotificationService.getErrorMessages());
        loadActions();
    }

    private void setErrorMessage(List<String> messages) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("Errors:\n");
        for (String s : messages){
            errorMessage.append(s).append("\n");
        }
        errorLabel.setText(errorMessage.toString());
    }
    
    private void loadActions(){
        Notificationactions actions = new Notificationactions();
        actions.setPane(pane);
        pane.setOnMouseEntered(actions.tackleIt);
    }

}
