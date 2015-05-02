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
import net.hft.dbproject.weatherapp.controller.SearchingDialogController;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.manager.Stagemanager;
import net.hft.dbproject.weatherapp.services.NotificationService;

/**
 *
 * @author Jan
 */
public class Searchpageactions {

    private SearchingDialogController controller;

    public Searchpageactions(SearchingDialogController controller) {
        this.controller = controller;
    }

    public EventHandler<ActionEvent> searchAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            String name = controller.getCityField().getText();
            String zip = controller.getZipField().getText();
            // Validation
            List<Control> controlsInError = new ArrayList<>();
            List<String> errorMessages = new ArrayList<>();
            boolean inError = false;
            int zipCode = 0;

            try {
                zipCode = Integer.decode(zip);
            } catch (NumberFormatException ex) {
                controlsInError.add(controller.getZipField());
                errorMessages.add("Zip code must a number!");
                inError = true;
            }
            if (name.isEmpty() && zip.isEmpty()) {
                controlsInError.add(controller.getCityField());
                controlsInError.add(controller.getZipField());
                errorMessages.add("Name or Zipcode must be filled!");
                inError = true;
            }
            if (inError) {
                NotificationService.fireNotification(controlsInError, errorMessages);
                new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/dialogs/Notification.fxml"), CSSFile.CSS_TEST, 350, 100, false);
            } else {
                //@TODO: ACTION??

                NotificationService.resetErrorBorder();
            }

        }
    };

}
