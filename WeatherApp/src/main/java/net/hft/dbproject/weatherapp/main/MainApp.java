package net.hft.dbproject.weatherapp.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.manager.StageFunctionalities;
import net.hft.dbproject.weatherapp.manager.Stagemanager;
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

public class MainApp extends Application {

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
    
    public static void main(String[] args) {
        try {
            URL url = null;
            HttpURLConnection urlConnect = null;
            url = new URL("http://api.openweathermap.org/data/2.5/weather?q=London,uk");
            urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setRequestMethod("GET");
            urlConnect.setRequestProperty("Authorization", "APPID=" + APIKEY);
            //urlConnect.addRequestProperty("x-api-key", APIKEY);
            //urlConnect.setRequestProperty("APPID=", APIKEY);
            urlConnect.setRequestProperty("Accept", "application/json");
            InputStreamReader inputStream = new InputStreamReader(urlConnect.getInputStream());
            BufferedReader br = new BufferedReader(inputStream);
            String output;
            System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
                    System.out.println(output);
		}
                
            Weather myWeather = readWeather(inputStream);
            System.out.println("Output from Class .... \n");
                    System.out.println(myWeather.toString());
		urlConnect.disconnect();
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
	  }
        
        launch(args);
	}
    
        public static Weather readWeather(InputStreamReader streamReader) throws IOException
        {
            JsonReader reader = new JsonReader (streamReader);
            String cname = "";
            reader.beginObject();
            while(reader.hasNext())
            {
                String name = reader.nextName();
                if (name.equals("name"))
                {
                    cname = reader.nextString();
                }     
            }
            reader.endObject();
            reader.close();
            return new Weather(cname);
        }
    }
    
    


