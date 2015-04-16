package net.hft.dbproject.weatherapp.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.manager.StageFunctionalities;
import net.hft.dbproject.weatherapp.manager.Stagemanager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class MainApp extends Application {

    private StageFunctionalities manager;
    private Logger logger;

    @Override
    public void start(Stage stage) throws Exception {
        BasicConfigurator.configure();
        this.logger = Logger.getLogger(MainApp.class);
        this.manager = new Stagemanager();
        stage.initStyle(StageStyle.UNDECORATED);
        manager.openStageAsRoot(stage, getClass().getResource("/fxml/mainpage/Scene.fxml"), CSSFile.CSS_DEFAULT, 251, 397);
        
        logger.info("MainApp started successfully");
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    

}
