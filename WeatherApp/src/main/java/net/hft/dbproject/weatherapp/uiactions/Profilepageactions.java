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
import net.hft.dbproject.weatherapp.controller.ProfileController;
import net.hft.dbproject.weatherapp.entities.AppUser;
import net.hft.dbproject.weatherapp.entities.UserBase;
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
public class Profilepageactions {

    ProfileController controller;
    private static final Logger LOGGER = LoggerFactory.getLogger(Profilepageactions.class);
    private UserBaseService userService;
    private String typedInOldPassword = "unknown";
    private String typedInUserName = "unknown";
    private String typedInNewPassword;
    private AppUser currentUser;

    public Profilepageactions() {

        this.controller = (ProfileController) ControllerContainer.getController(ProfileController.class);

    }

    public EventHandler<ActionEvent> saveAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {

            typedInUserName = controller.getPnameField().getText();
            typedInOldPassword = controller.getOldpasswordField().getText();
            typedInNewPassword = controller.getNewpasswordField().getText();

            // Validation
            List<Control> controlsInError = new ArrayList<>();
            List<String> errorMessages = new ArrayList<>();
            boolean inError = false;
            if (typedInUserName.isEmpty() || typedInOldPassword.isEmpty() || typedInNewPassword.isEmpty() || typedInUserName.equals("unknown")
                    || typedInOldPassword.equals("unknown")) {
                controlsInError.add(controller.getPnameField());
                controlsInError.add(controller.getOldpasswordField());
                controlsInError.add(controller.getNewpasswordField());
                errorMessages.add("All Values have to be filled!");
                inError = true;
            }
            if (inError) {
                NotificationService.fireNotification(controlsInError, errorMessages);
                new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/dialogs/Notification.fxml"), CSSFile.CSS_TEST, 350, 100, false);
            } else {
                userService = new UserService();
                AppUser puser = LoggedInUser.getInstance();
                UserService us = new UserService();
                us.updatePasswortByUserId(puser.getId(), typedInNewPassword);

                if (isPswValid(puser)) {
                    us.updatePasswortByUserId(puser.getId().intValue(), typedInNewPassword);
                    LOGGER.info("New Password Changed:{}", puser.getName());
                    NotificationService.resetErrorBorder();

                }
                currentUser = LoggedInUser.getInstance();

            }
            controller.getPnameField().setText(currentUser.getName());
        }
    };

    private boolean isPswValid(AppUser opw) {
        boolean isValid = false;
        if (opw.getPassword().equals(typedInOldPassword)) {
            isValid = true;
        }
        return isValid;
    }

    public EventHandler<ActionEvent> deleteAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {

            typedInUserName = controller.getPnameField().getText();
            typedInOldPassword = controller.getOldpasswordField().getText();

            List<Control> controlsInError = new ArrayList<>();
            List<String> errorMessages = new ArrayList<>();
            boolean inError = false;
            if (typedInUserName.isEmpty() || typedInOldPassword.isEmpty() || typedInUserName.equals("unknown")
                    || typedInOldPassword.equals("unknown")) {
                controlsInError.add(controller.getPnameField());
                controlsInError.add(controller.getOldpasswordField());
                errorMessages.add("User name and old password have to be filled!");
                inError = true;
            }
            if (inError) {
                NotificationService.fireNotification(controlsInError, errorMessages);
                new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/dialogs/Notification.fxml"), CSSFile.CSS_TEST, 350, 100, false);
            } else {
                userService = new UserService();
                AppUser puser = LoggedInUser.getInstance();
                UserService dus = new UserService();
                dus.deleteUser(puser.getId());
                if (isPswValid(puser)) {

                    LOGGER.info("User Deleted:{}", puser.getName());
                    NotificationService.resetErrorBorder();

                }
            }
        }
    };
}
