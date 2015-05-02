package net.hft.dbproject.weatherapp.uiactions;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.controller.MainpageController;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.services.NotificationService;
import net.hft.dbproject.weatherapp.services.PropertiesService;
import net.hft.dbproject.weatherapp.manager.StageFunctionalities;
import net.hft.dbproject.weatherapp.manager.Stagemanager;

/**
 *
 * @author Jan
 */
public class Mainpageactions {

    StageFunctionalities functions = new Stagemanager();

    // Current mouseposition
    private Point mousePosition = new Point();

    // Passed pane for actions
    private Pane pane;

    private MainpageController controller;

    PropertiesService propertiesService;

    public Mainpageactions(MainpageController controller) {
        this.controller = controller;
        this.propertiesService = new PropertiesService();
    }
    /**
     * Loads the mouse- data in local variable.
     */
    public EventHandler<MouseEvent> trackMousePosition = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            mousePosition.x = (int) t.getX();
            mousePosition.y = (int) t.getY();
        }
    };
    /**
     * Enables Drag-Drop for setting window- Positions.
     */
    public EventHandler<MouseEvent> movePane = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if (pane == null) {
                throw new IllegalArgumentException("Pane is not set. Please use setPane() before calling this method.");
            }
            pane.getScene().getWindow().setX(t.getScreenX() - mousePosition.x);
            pane.getScene().getWindow().setY(t.getScreenY() - mousePosition.y);
        }
    };

    public EventHandler<MouseEvent> exitEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            System.exit(0);
        }
    };

    public EventHandler<ActionEvent> searchAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
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
            if (name.isEmpty() && zip.isEmpty()) {
                controlsInError.add(controller.getNameField());
                controlsInError.add(controller.getZipField());
                errorMessages.add("Name or Zipcode must be filled!");
                inError = true;
            }
            if (inError) {
                NotificationService.fireNotification(controlsInError, errorMessages);
                new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/dialogs/Notification.fxml"), CSSFile.CSS_TEST, 350, 100, false);
            } else {
                new PropertiesService().storeCityAndZip(name, zip);
                NotificationService.resetErrorBorder();
            }

        }
    };

    public EventHandler<ActionEvent> openRegisterPage = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            functions.openStageAsRoot(null, getClass().getResource("/fxml/mainpage/Register.fxml"), CSSFile.CSS_DEFAULT, 221, 153, true);
        }
    };

    public EventHandler<ActionEvent> openLoginPage = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            functions.openStageAsRoot(null, getClass().getResource("/fxml/mainpage/login.fxml"), CSSFile.CSS_DEFAULT, 251, 397, true);
        }
    };

    private EventHandler<MouseEvent> cHoverAction = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            controller.getcImage().setImage(new Image(getClass().getResource("/images/UI/c_3.png").toString()));
        }
    };
    private EventHandler<MouseEvent> cExitHoverAction = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            controller.getcImage().setImage(new Image(getClass().getResource("/images/UI/c_1.png").toString()));
        }
    };

    private EventHandler<MouseEvent> fHoverAction = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            controller.getfImage().setImage(new Image(getClass().getResource("/images/UI/f_3.png").toString()));
        }
    };
    private EventHandler<MouseEvent> fExitHoverAction = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            controller.getfImage().setImage(new Image(getClass().getResource("/images/UI/f_1.png").toString()));
        }
    };

    public EventHandler<MouseEvent> cClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            controller.getcImage().setOnMouseEntered(null);
            controller.getcImage().setOnMouseExited(null);
            controller.getcImage().setImage(new Image(getClass().getResource("/images/UI/c_2.png").toString()));
            controller.getfImage().setImage(new Image(getClass().getResource("/images/UI/f_1.png").toString()));
            controller.getfImage().setOnMouseEntered(fHoverAction);
            controller.getfImage().setOnMouseExited(fExitHoverAction);
        }
    };

    private void storeCalculation(String calc) {
        propertiesService.storeCalculation(calc);
    }

    public EventHandler<MouseEvent> fClickSave = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            fClick.handle(t);
            storeCalculation("F");
        }
    };
    public EventHandler<MouseEvent> cClickSave = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            cClick.handle(t);
            storeCalculation("C");
        }
    };

    public EventHandler<MouseEvent> fClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            controller.getfImage().setOnMouseEntered(null);
            controller.getfImage().setOnMouseExited(null);
            controller.getfImage().setImage(new Image(getClass().getResource("/images/UI/f_2.png").toString()));
            controller.getcImage().setImage(new Image(getClass().getResource("/images/UI/c_1.png").toString()));
            controller.getcImage().setOnMouseEntered(cHoverAction);
            controller.getcImage().setOnMouseExited(cExitHoverAction);
        }
    };

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
