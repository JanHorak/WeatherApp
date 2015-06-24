package net.hft.dbproject.weatherapp.services;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Control;

/**
 *
 * @author Jan
 */
public abstract class NotificationService {

    private final static List<Control> LAST_FIELDS_IN_ERROR = new ArrayList<>();

    private static List<String> errorMessages = new ArrayList<>();

    public static void fireNotification(List<Control> controlsInError, List<String> errors) {
        errorMessages = errors;
        for (Control c : controlsInError) {
            LAST_FIELDS_IN_ERROR.add(c);
            c.setStyle("-fx-border-color: red");
        }
    }

    public static void resetErrorBorder() {
        for (Control c : LAST_FIELDS_IN_ERROR) {
            c.setStyle("-fx-border-color: default");
        }
        LAST_FIELDS_IN_ERROR.clear();
    }

    public static List<String> getErrorMessages() {
        return errorMessages;
    }

}
