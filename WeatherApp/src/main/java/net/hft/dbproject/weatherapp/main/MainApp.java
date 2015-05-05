package net.hft.dbproject.weatherapp.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.manager.StageFunctionalities;
import net.hft.dbproject.weatherapp.manager.Stagemanager;
import net.hft.dbproject.weatherapp.services.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.hft.dbproject.weatherapp.entities.Weather;

import java.io.StringReader;
import java.util.logging.Level;
import net.hft.dbproject.weatherapp.services.WeatherService;

public class MainApp extends Application 
{

    private StageFunctionalities manager;
    private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);
    private static final String APIKEY = "8207b192ff2c645813be5259681c74d6";
    


    @Override
    public void start(Stage stage) throws Exception {
        this.manager = new Stagemanager();
        manager.openStageAsRoot(stage, getClass().getResource("/fxml/mainpage/Scene.fxml"), CSSFile.CSS_DEFAULT, 251, 397, false);
        
       
	  
                
                
        LOGGER.info("MainApp started successfully");
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    
    public static void main(String[] args) 
    {
        QueryWeather("London");
        
        launch(args);
    }
    
    public static void QueryWeather(String cityName)
    {
        try {
            //URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Stuttgart,d");
            String urlText = String.format("http://api.openweathermap.org/data/2.5/find?q=%s&type=like", cityName);
            URL url = new URL(urlText);
            
            WeatherService service = new WeatherService(url, APIKEY);
            for(int i =0; i<service.WeatherArray.size(); i++)
            {
                System.out.println("City number " + i + "is");
                System.out.println(service.WeatherArray.get(i).getCityName());
                System.out.println("Temp is ");
                System.out.println(service.WeatherArray.get(i).getTemperature());
                System.out.println("Min Temp is ");
                System.out.println(service.WeatherArray.get(i).getMinTemperature());
                System.out.println("Max Temp is ");
                System.out.println(service.WeatherArray.get(i).getMaxTemperature());   
            }
            
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(WeatherService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
     
    
    


