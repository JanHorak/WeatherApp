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
import net.hft.dbproject.weatherapp.entities.WeatherImage;
import net.hft.dbproject.weatherapp.persistence.WeatherBaseService;
import net.hft.dbproject.weatherapp.persistence.WeatherPersistenceService;

/**
 *
 * @author Jan
 */
public abstract class JSONParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONParser.class);

    public static List<WeatherInformation> toWeather(InputStreamReader streamReader) throws IOException {

        List<WeatherInformation> result = new ArrayList<WeatherInformation>();
       
        JsonReader reader = new JsonReader (streamReader);
        reader.setLenient(true);
        String cCityName = "";
        Temperature cityTemperature = null;
        double cTemp = 0.0;
        double cTempMin = 0.0;
        double cTempMax = 0.0;
        String cCountryCode = "";
        int imageIconID = 0;
        boolean isDayWeather = true;
        String weatherDescription = "";
        WeatherBaseService weatherBaseService = new WeatherPersistenceService();
                
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
                            cCityName = reader.nextString();
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
                                        cTempMin = reader.nextDouble();
                                    }
                                else if (name2.equals("temp_max"))
                                    { 
                                        cTempMax = reader.nextDouble();
                                    }
                                else
                                    { reader.skipValue();}
                            }
                            reader.endObject();
                            cityTemperature = new Temperature(cTemp, cTempMin, cTempMax);
                        }
                        else if (name1.equals("sys"))
                            {
                                reader.beginObject();
                                while(reader.hasNext())
                                {
                                    String name3 = reader.nextName();
                                    if (name3.equals("country"))
                                    {
                                        cCountryCode = reader.nextString();
                                    }
                                }
                                reader.endObject();
                            }
                        else if (name1.equals("weather"))
                            {
                                reader.beginArray();
                                while(reader.hasNext())
                                {
                                    reader.beginObject();
                                    while(reader.hasNext())
                                    {
                                        String name4 = reader.nextName();
                                        if (name4.equals("id"))
                                        {
                                            imageIconID = reader.nextInt();
                                        }
                                        else if (name4.equals("description"))
                                        {
                                            weatherDescription = reader.nextString();
                                        }
                                        else if (name4.equals("icon"))
                                        {
                                            String imageDayNigthIndication = reader.nextString();
                                            isDayWeather = imageDayNigthIndication.endsWith("d");
                                        }
                                        else
                                        { 
                                            reader.skipValue();
                                        }
                                    }
                                    reader.endObject();
                                }
                                reader.endArray();
                            }
                        else 
                            reader.skipValue();
                    }
                    WeatherInformation currWeather = new WeatherInformation(cCityName, cCountryCode, weatherDescription, cityTemperature);
                    WeatherImage weatherImage = weatherBaseService.getImageByIconID(imageIconID); // fetch weather image by imageIconID
                    // Make use of isDayWeather?
                    currWeather.setImage(weatherImage);
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
        PrintToLog(result);
       return result;
    }
    
    static private void PrintToLog(List<WeatherInformation> weatherList )
    {
        LOGGER.info("Weather requested from API:");
        if (weatherList.size() == 0)
        {
            LOGGER.info("No weather information available");
            return;
        }
            
        for(int i =0; i<weatherList.size(); i++)
        {
            LOGGER.info("City={}, Country ={}, temp={}, minTemp={}, maxTemp={}"
                    , weatherList.get(i).getCityName()
                    , weatherList.get(i).getcountryCode()
                    , weatherList.get(i).getTemperature().getAverageTemp()
                    , weatherList.get(i).getTemperature().getMinTemp()
                    , weatherList.get(i).getTemperature().getMaxTemp()
            );
        }
    }
}
