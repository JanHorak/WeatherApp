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

}
