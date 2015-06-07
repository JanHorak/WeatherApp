/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.uiactions;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import net.hft.dbproject.weatherapp.controller.LoginController;
import net.hft.dbproject.weatherapp.entities.AppUser;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.helper.LoggedInUser;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.manager.Stagemanager;
import net.hft.dbproject.weatherapp.persistence.UserBaseService;
import net.hft.dbproject.weatherapp.persistence.UserService;
import net.hft.dbproject.weatherapp.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author AVATSP
 */
public class Loginpageactions {

    private static final Logger LOGGER = LoggerFactory.getLogger(Loginpageactions.class);
    private UserBaseService userservice;
    private String typenInPassword = "unknown";
    private String typedInUserName = "unknown";
    private LoginController controller;

    public Loginpageactions() {
        this.controller = (LoginController) ControllerContainer.getController(LoginController.class);
    }

    public EventHandler<ActionEvent> loginAction = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {
            AppUser liu = null;
            typedInUserName = controller.getNameField().getText();
            typenInPassword = controller.getPasswordField().getText();
            // Validation
            List<Control> controlsInError = new ArrayList<>();
            List<String> errorMessages = new ArrayList<>();
            boolean inError = false;

            if (typedInUserName.isEmpty() || typenInPassword.isEmpty()
                    || typedInUserName.equals("unknown")
                    || typenInPassword.equals("unknown")) {
                controlsInError.add(controller.getNameField());
                controlsInError.add(controller.getPasswordField());
                errorMessages.add("Both values have to be filled!");
                inError = true;
            }
            if (inError) {
                NotificationService.fireNotification(controlsInError, errorMessages);
                new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/dialogs/Notification.fxml"), CSSFile.CSS_TEST, 350, 100, false);
            } else {
                userservice = new UserService();
                AppUser user = (AppUser) userservice.getUserByName(controller.getNameField().getText());

                if (isPswValid(user)) {
                    liu = LoggedInUser.getInstance();
                    LOGGER.info("User is Logged:");

                    liu = user;
                    LOGGER.info("Welcome User : {}", user.getName());
                    controller.getNameField().setText("");
                    controller.getPasswordField().setText("");
                    new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/mainpage/Dashboard.fxml"), CSSFile.CSS_DEFAULT, 251, 397, true);

                } else {
                    errorMessages.add("Username or Password are invalid!");
                    NotificationService.fireNotification(controlsInError, errorMessages);
                    new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/dialogs/Notification.fxml"), CSSFile.CSS_TEST, 350, 100, false);
                    LOGGER.info("Not a Valid User and Password");
                }
                NotificationService.resetErrorBorder();

            }
        }
    };

    private boolean isPswValid(AppUser psw) {
        boolean isValid = false;
        if (psw.getPassword().equals(typenInPassword)) {
            isValid = true;
        }
        return isValid;
    }
}
