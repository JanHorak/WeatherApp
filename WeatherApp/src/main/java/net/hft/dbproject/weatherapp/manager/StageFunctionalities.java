package net.hft.dbproject.weatherapp.manager;

import java.net.URL;
import javafx.stage.Stage;
import net.hft.dbproject.weatherapp.enums.CSSFile;

/**
 *
 * This interface defines the mainfunctionalities for the common stagemanager.
 * @author Jan
 */
public interface StageFunctionalities {
    
    /**
     * Opens a new Scene as a root. This is the 
     * common way for e.g. Dialogs.
     * 
     * @param stage
     * @param pathToFXML
     * @param pathToCSS 
     * @param width 
     * @param height 
     */
    public void openStageAsRoot(Stage stage, URL pathToFXML, CSSFile pathToCSS, int width, int height);
    
   
    
    
}
