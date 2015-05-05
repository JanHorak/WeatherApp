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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import net.hft.dbproject.weatherapp.entities.Temperature;

/**
 *
 * @author admin
 */
public class WeatherService {
    
    public List<Weather> WeatherArray = new ArrayList<Weather>();
    
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
            
            BufferedReader br = new BufferedReader(inputStream);
                    String output;
            //System.out.println("Output from Server .... \n");
		//while ((output = br.readLine()) != null) {
                    //System.out.println(output);
                    parseWeather(inputStream);
                    
		//}
		urlConnect.disconnect();
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
	  }
        
	}
    
        private void parseWeather(InputStreamReader streamReader) throws IOException
        {
            WeatherArray.clear();
            
            JsonReader reader = new JsonReader (streamReader);
            reader.setLenient(true);
            String cname = null;
            Temperature cityTemperature = null;
            Double cTemp = null;
            Double cTemp_min = null;
            Double cTemp_max = null;
            
            reader.beginObject();
            while(reader.hasNext())
            {
                String name = reader.nextName();
                if (name.equals("list"))
                {
                    reader.beginArray();
                    while(reader.hasNext())
                    {
                        reader.beginObject();
                        while(reader.hasNext())
                        {
                            String name1 = reader.nextName();
                            if (name1.equals("name"))
                            {
                                cname = reader.nextString();
                            } 
                            else if (name1.equals("main"))
                            {
                                //cityTemperature = readTemperature(reader);
                                reader.beginObject();
                                while(reader.hasNext())
                                {
                                    String name2 = reader.nextName();
                                    if (name2.equals("temp"))
                                        { 
                                            cTemp = reader.nextDouble();
                                        }
                                    else if (name2.equals("temp_min"))
                                        { 
                                            cTemp_min = reader.nextDouble();
                                        }
                                    else if (name2.equals("temp_max"))
                                        { 
                                            cTemp_max = reader.nextDouble();
                                        }
                                    else
                                        { reader.skipValue();}
                                }
                                reader.endObject();
                                cityTemperature = new Temperature(cTemp, cTemp_min, cTemp_max);
                            }
                            else 
                                reader.skipValue();
                        }
                        Weather currWeather = new Weather(cname,cityTemperature);
                        WeatherArray.add(currWeather);
                        reader.endObject();
                    }
                    reader.endArray();
                }
                else 
                    reader.skipValue();
            }
            reader.endObject();
            
            reader.close();
            //return new Weather(cname);
           // Weather currWeather = new Weather(cname,cityTemperature);
           // WeatherArray.add(currWeather);
        }
}
    

