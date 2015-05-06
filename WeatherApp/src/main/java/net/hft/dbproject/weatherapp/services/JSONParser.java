/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.services;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import net.hft.dbproject.weatherapp.entities.Temperature;

/**
 *
 * @author Jan
 */
public abstract class JSONParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONParser.class);

    public static WeatherInformation toWeather(InputStreamReader streamReader) throws IOException {
        WeatherInformation result = new WeatherInformation();
        JsonReader reader = new JsonReader(streamReader);
        reader.setLenient(true);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                result.setCityName(reader.nextString());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        reader.close();
        LOGGER.info("Weather ({}) requested from API", result.getCityName());
        return result;
    }

    public static List<WeatherInformation> toWeathers(InputStreamReader streamReader) throws IOException {
       List<WeatherInformation> result = new ArrayList<WeatherInformation>();
       
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
                        WeatherInformation currWeather = new WeatherInformation(cname, "", cityTemperature);
                        result.add(currWeather);
                        reader.endObject();
                    }
                    reader.endArray();
                }
                else 
                    reader.skipValue();
            }
            reader.endObject();
            
            reader.close();       
       return result;
    }
}
