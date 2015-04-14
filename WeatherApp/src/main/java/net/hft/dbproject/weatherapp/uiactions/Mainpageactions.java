package net.hft.dbproject.weatherapp.uiactions;

import java.awt.Point;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Jan
 */
public class Mainpageactions {

    // Current mouseposition
    private Point mousePosition = new Point();

    // Passed pane for actions
    private Pane pane;

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


    public void setPane(Pane pane) {
        this.pane = pane;
    }

}
