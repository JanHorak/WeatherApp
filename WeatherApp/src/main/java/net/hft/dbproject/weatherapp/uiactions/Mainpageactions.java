package net.hft.dbproject.weatherapp.uiactions;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.controller.MainpageController;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.manager.Stagemanager;
import net.hft.dbproject.weatherapp.services.NotificationService;
import net.hft.dbproject.weatherapp.services.PropertiesService;

/**
 *
 * @author Jan
 */
public class Mainpageactions {

    // Current mouseposition
    private Point mousePosition = new Point();

    // Passed pane for actions
    private Pane pane;

    private MainpageController controller;

    public Mainpageactions(MainpageController controller) {
        this.controller = controller;
    }
    /**
     * Loads the mouse- data in local variable.
     */
    public EventHandler<MouseEvent> trackMousePosition = (MouseEvent t) -> {
        mousePosition.x = (int) t.getX();
        mousePosition.y = (int) t.getY();
    };

    /**
     * Enables Drag-Drop for setting window- Positions.
     */
    public EventHandler<MouseEvent> movePane = (MouseEvent t) -> {
        if (pane == null) {
            throw new IllegalArgumentException("Pane is not set. Please use setPane() before calling this method.");
        }
        pane.getScene().getWindow().setX(t.getScreenX() - mousePosition.x);
        pane.getScene().getWindow().setY(t.getScreenY() - mousePosition.y);
    };

    public EventHandler<MouseEvent> exitEvent = (MouseEvent t) -> {
        System.exit(0);
    };

    public EventHandler<ActionEvent> searchAction = (ActionEvent t) -> {
        String name = controller.getNameField().getText();
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
        if (name.isEmpty()) {
            controlsInError.add(controller.getNameField());
            errorMessages.add("Name must be filled!");
            inError = true;
        }
        if (zip.isEmpty()) {
            controlsInError.add(controller.getZipField());
            errorMessages.add("Zip code must be filled!");
            inError = true;
        }
        if (inError) {
            NotificationService.fireNotification(controlsInError, errorMessages);
            new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/dialogs/Notification.fxml"), CSSFile.CSS_TEST, 350, 150, false);
        } else {
            new PropertiesService().storeCityAndZip(name, zip);
            NotificationService.resetErrorBorder();
        }

    };
    

    public void setPane(Pane pane) {
        this.pane = pane;
    }

}
