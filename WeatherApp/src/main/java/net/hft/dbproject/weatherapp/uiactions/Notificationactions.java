package net.hft.dbproject.weatherapp.uiactions;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Jan
 */
public class Notificationactions {

    // Passed pane for actions
    private Pane pane;

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public EventHandler<MouseEvent> tackleIt = (MouseEvent t) -> {
        double step = 0.00001;
        for (double i = 1; i > 0; i = i - step) {
            pane.getScene().getWindow().setOpacity(i);
        }
        Stage s = (Stage) pane.getScene().getWindow();
        s.close();
    };

}
