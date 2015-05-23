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
import net.hft.dbproject.weatherapp.controller.RegisterController;
import net.hft.dbproject.weatherapp.entities.AppUser;
import net.hft.dbproject.weatherapp.entities.UserBase;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.manager.Stagemanager;
import net.hft.dbproject.weatherapp.persistence.UserBaseService;
import net.hft.dbproject.weatherapp.persistence.UserService;
import net.hft.dbproject.weatherapp.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jan
 */
public class Registerpageactions {

    private static final Logger LOGGER = LoggerFactory.getLogger(Registerpageactions.class);

    public Registerpageactions() {
        this.controller = (RegisterController) ControllerContainer.getController(RegisterController.class);
    }

    private RegisterController controller;

    private UserBaseService userService;

    public EventHandler<ActionEvent> registerAction = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {
            String name = controller.getNameField().getText();
            String pw = controller.getPasswordField().getText();
            // Validation
            List<Control> controlsInError = new ArrayList<>();
            List<String> errorMessages = new ArrayList<>();
            boolean inError = false;

            if (name.isEmpty() || pw.isEmpty()) {
                controlsInError.add(controller.getNameField());
                controlsInError.add(controller.getPasswordField());
                errorMessages.add("Both values have to be filled!");
                inError = true;
            }
            if (inError) {
                NotificationService.fireNotification(controlsInError, errorMessages);
                new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/dialogs/Notification.fxml"), CSSFile.CSS_TEST, 350, 100, false);
            } else {
                userService = new UserService();
                UserBase user = new AppUser();
                user.setName(name);
                user.setPassword(pw);
                userService.saveNewUser(user);
                LOGGER.info("New User is saved: {}", user.getName());
                NotificationService.resetErrorBorder();
                controller.getNameField().setText("");
                controller.getPasswordField().setText("");

            }
        }
    };

}
