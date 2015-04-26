/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.hft.dbproject.weatherapp.entities.Weather;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
/**
 *
 * @author admin
 */
public class WeatherService {
    
    
    public WeatherService(URL myUrl, String key)
    {
    try {
            URL url = null;
            HttpURLConnection urlConnect = null;
            url = myUrl;
            urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setRequestMethod("GET");
            urlConnect.setRequestProperty("Authorization", "APPID=" + key);
            urlConnect.setRequestProperty("Accept", "application/json");
            InputStreamReader inputStream = new InputStreamReader(urlConnect.getInputStream());
            Weather myWeather = parseWeather(inputStream);
            System.out.println("Output from Class .... \n");
                    System.out.println(myWeather.toString());
		urlConnect.disconnect();
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
	  }
        
	}
    
        private Weather parseWeather(InputStreamReader streamReader) throws IOException
        {
            JsonReader reader = new JsonReader (streamReader);
            reader.setLenient(true);
            String cname = null;
            reader.beginObject();
            while(reader.hasNext())
            {
                String name = reader.nextName();
                if (name.equals("name"))
                {
                    cname = reader.nextString();
                }  
                else 
                    reader.skipValue();
            }
            reader.endObject();
            reader.close();
            return new Weather(cname);
        }
}
    

