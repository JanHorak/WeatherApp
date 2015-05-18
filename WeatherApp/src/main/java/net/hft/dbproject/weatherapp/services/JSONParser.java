/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.services;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStream;
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
public class JSONParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONParser.class);
    
    private InputStreamReader queryInputStreamReader;
    private JsonReader queryJSONReader;

    public  JSONParser()
    {
    }
    
    private void initialize(InputStream queryResult) throws IOException 
    {
        queryInputStreamReader = new InputStreamReader(queryResult);
        queryJSONReader = new JsonReader (queryInputStreamReader);
        queryJSONReader.setLenient(true);
    }
    
    private void deInitialize() throws IOException 
    {
        queryJSONReader.close();  
        queryInputStreamReader.close();
    }
    
    // Parses weather for JSON containing info of one city 
    public WeatherInformation toWeather(InputStream queryResult) throws IOException {
        
        initialize(queryResult);
        boolean setCityImage = true;
        WeatherInformation result = parseCityWeather(setCityImage);
        deInitialize();
        
        printToLog(result);
        return result;
    }    
    
    // Parses weather for JSON containing data of a cities list 
    public List<WeatherInformation> toWeatherList(InputStream queryResult) throws IOException {

        initialize(queryResult);

        List<WeatherInformation> result = new ArrayList<WeatherInformation>();
                       
        queryJSONReader.beginObject();
        while(queryJSONReader.hasNext())
        {
            String name = queryJSONReader.nextName();
            if (name.equals("list"))
            {
                queryJSONReader.beginArray();
                while(queryJSONReader.hasNext())
                {
                    boolean dontSetCityImage = false;
                    WeatherInformation currWeather = parseCityWeather(dontSetCityImage);
                    if (currWeather != null)
                    {
                        result.add(currWeather);
                    }
                }
                queryJSONReader.endArray();
            }
            else 
                queryJSONReader.skipValue();
        }
        queryJSONReader.endObject();

        deInitialize();
        
       printToLog(result);
       return result;
    }       
    
    private WeatherInformation parseCityWeather(boolean setCityImage) throws IOException
    {
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
                
        queryJSONReader.beginObject();
        while(queryJSONReader.hasNext())
        {
            String name1 = queryJSONReader.nextName();
            if (name1.equals("name"))
            {
                cCityName = queryJSONReader.nextString();
            } 
            else if (name1.equals("main"))
            {
                //cityTemperature = readTemperature(queryJSONReader);
                queryJSONReader.beginObject();
                while(queryJSONReader.hasNext())
                {
                    String name2 = queryJSONReader.nextName();
                    if (name2.equals("temp"))
                        { 
                            cTemp = queryJSONReader.nextDouble();
                        }
                    else if (name2.equals("temp_min"))
                        { 
                            cTempMin = queryJSONReader.nextDouble();
                        }
                    else if (name2.equals("temp_max"))
                        { 
                            cTempMax = queryJSONReader.nextDouble();
                        }
                    else
                    { 
                        queryJSONReader.skipValue();
                    }
                }
                queryJSONReader.endObject();
                cityTemperature = new Temperature(cTemp, cTempMin, cTempMax);
            }
            else if (name1.equals("sys"))
                {
                    queryJSONReader.beginObject();
                    while(queryJSONReader.hasNext())
                    {
                        String name3 = queryJSONReader.nextName();
                        if (name3.equals("country"))
                        {
                            cCountryCode = queryJSONReader.nextString();
                        }
                        else
                        { 
                            queryJSONReader.skipValue();
                        }
                    }
                    queryJSONReader.endObject();
                }
            else if (name1.equals("weather"))
                {
                    queryJSONReader.beginArray();
                    while(queryJSONReader.hasNext())
                    {
                        queryJSONReader.beginObject();
                        while(queryJSONReader.hasNext())
                        {
                            String name4 = queryJSONReader.nextName();
                            if (name4.equals("id"))
                            {
                                imageIconID = queryJSONReader.nextInt();
                            }
                            else if (name4.equals("description"))
                            {
                                weatherDescription = queryJSONReader.nextString();
                            }
                            else if (name4.equals("icon"))
                            {
                                String imageDayNigthIndication = queryJSONReader.nextString();
                                isDayWeather = imageDayNigthIndication.endsWith("d");
                            }
                            else
                            { 
                                queryJSONReader.skipValue();
                            }
                        }
                        queryJSONReader.endObject();
                    }
                    queryJSONReader.endArray();
                }
            else 
                queryJSONReader.skipValue();
        }
        queryJSONReader.endObject();

        WeatherInformation currWeather = new WeatherInformation(cCityName, cCountryCode, weatherDescription, cityTemperature);
        if (setCityImage)
        {
            WeatherImage weatherImage = weatherBaseService.getImageByIconID(imageIconID); // fetch weather image by imageIconID
            // Make use of isDayWeather?
            currWeather.setImage(weatherImage);
        }
        return currWeather;        
    }

    
    static private void printToLog(List<WeatherInformation> weatherList )
    {
        if (weatherList.size() == 0)
        {
            LOGGER.info("No weather information available");
            return;
        }
            
        for(int i =0; i<weatherList.size(); i++)
        {
            printToLog(weatherList.get(i));
        }
    }
    
    static private void printToLog(WeatherInformation weatherInfo )
    {
        if (weatherInfo == null)
        {
            LOGGER.info("No weather information available");
            return;
        }
        LOGGER.info("City={}, Country ={}, temp={}, minTemp={}, maxTemp={}"
                , weatherInfo.getCityName()
                , weatherInfo.getcountryCode()
                , weatherInfo.getTemperature().getAverageTemp()
                , weatherInfo.getTemperature().getMinTemp()
                , weatherInfo.getTemperature().getMaxTemp()
                );
    }
}
