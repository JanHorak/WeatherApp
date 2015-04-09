package net.hft.dbproject.weatherapp.manager;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.hft.dbproject.weatherapp.enums.CSSFile;

/**
 *
 * @author Jan
 */
public class Stagemanager implements StageFunctionalities {

    @Override
    public void openStageAsRoot(Stage stage, URL pathToFXML, CSSFile pathToCSS, int width, int height) {
        Parent root = null;
        if (stage == null){
            throw new IllegalArgumentException("Stage not found!");
        }
        if (pathToFXML == null || pathToFXML.getFile() == null || pathToFXML.getFile().isEmpty()) {
            throw new IllegalArgumentException("Path to FXML not found!");
        }
        if (pathToCSS == null || pathToCSS.toString().isEmpty()) {
            //@Todo: print debug- warning
            
            // Use default CSS
            pathToCSS = CSSFile.CSS_DEFAULT;
        }

        try {
            root = FXMLLoader.load(pathToFXML);
        } catch (IOException ex) {
            //@Todo: change catch- Block
            Logger.getLogger(Stagemanager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(pathToCSS.toString());
        stage.setScene(scene);
        stage.show();
    }

    
    
}
