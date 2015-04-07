package net.hft.dbproject.weatherapp.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.manager.StageFunctionalities;
import net.hft.dbproject.weatherapp.manager.Stagemanager;

public class MainApp extends Application {

    private StageFunctionalities manager;

    @Override
    public void start(Stage stage) throws Exception {
        this.manager = new Stagemanager();
        manager.openStageAsRoot(stage, getClass().getResource("/fxml/mainpage/Scene.fxml"), CSSFile.CSS_DEFAULT, 350, 150);
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
