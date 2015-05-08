package net.hft.dbproject.weatherapp.uiactions;

import java.awt.Point;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import net.hft.dbproject.weatherapp.controller.MainpageController;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.services.PropertiesService;
import net.hft.dbproject.weatherapp.manager.StageFunctionalities;
import net.hft.dbproject.weatherapp.manager.Stagemanager;
import net.hft.dbproject.weatherapp.utilities.Utilities;

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

    public Mainpageactions() {
        this.controller = (MainpageController) ControllerContainer.getController(MainpageController.class);
        this.propertiesService = new PropertiesService();
    }

    
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
            if (controller.getMainpagePane() == null) {
                throw new IllegalArgumentException("Pane is not set. Please use setPane() before calling this method.");
            }
            controller.getMainpagePane().getScene().getWindow().setX(t.getScreenX() - mousePosition.x);
            controller.getMainpagePane().getScene().getWindow().setY(t.getScreenY() - mousePosition.y);
        }
    };

    public EventHandler<MouseEvent> exitEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            System.exit(0);
        }
    };

    public EventHandler<ActionEvent> openSearchDialog = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            new Stagemanager().openStageAsRoot(null, getClass().getResource("/fxml/dialogs/SearchingDialog.fxml"), CSSFile.CSS_TEST, 263, 294, true);
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
            
            String minval = String.valueOf(Utilities.toCelsius(Double.valueOf(controller.getMinTempValue().getText())));
            controller.getMinTempValue().setText(minval);
            String maxval = String.valueOf(Utilities.toCelsius(Double.valueOf(controller.getMaxTempValue().getText())));
            controller.getMaxTempValue().setText(maxval);
            String avgval = String.valueOf(Utilities.toCelsius(Double.valueOf(controller.getAvgTempValue().getText())));
            controller.getAvgTempValue().setText(avgval);
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
            
            String minval = String.valueOf(Utilities.toFahrenheit(Double.valueOf(controller.getMinTempValue().getText())));
            controller.getMinTempValue().setText(minval);
            String maxval = String.valueOf(Utilities.toFahrenheit(Double.valueOf(controller.getMaxTempValue().getText())));
            controller.getMaxTempValue().setText(maxval);
            String avgval = String.valueOf(Utilities.toFahrenheit(Double.valueOf(controller.getAvgTempValue().getText())));
            controller.getAvgTempValue().setText(avgval);
        }
    };

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
