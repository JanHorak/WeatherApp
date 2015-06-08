package net.hft.dbproject.weatherapp.manager;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.hft.dbproject.weatherapp.controller.MainpageController;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jan
 */
public class Stagemanager implements StageFunctionalities {

    private static final Logger LOGGER = LoggerFactory.getLogger(Stagemanager.class);

    @Override
    public void openStageAsRoot(Stage stage, URL pathToFXML, CSSFile pathToCSS, int width, int height, boolean decorated) {
        Parent root = null;
        if (stage == null) {
            stage = new Stage();
        }
        if (pathToFXML == null || pathToFXML.getFile() == null || pathToFXML.getFile().isEmpty()) {
            throw new IllegalArgumentException("Path to FXML not found!");
        }
        if (pathToCSS == null || pathToCSS.toString().isEmpty()) {
            LOGGER.warn("No CSS- File passed. The default-CSS will be used.");
            pathToCSS = CSSFile.CSS_DEFAULT;
        }

        try {
            root = FXMLLoader.load(pathToFXML);
        } catch (IOException ex) {
            LOGGER.error(ex.toString());
        }
        if (!decorated) {
            stage.initStyle(StageStyle.UNDECORATED);
        }
        Scene scene = new Scene(root, width, height, Color.TRANSPARENT);
        scene.getStylesheets().add(pathToCSS.toString());
        stage.setScene(scene);
        stage.show();
    }

}
