/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.services;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.hft.dbproject.weatherapp.entities.Weather;

/**
 *
 * @author Jan
 */
public abstract class JSONParser {

    public static Weather toWeather(InputStreamReader streamReader) throws IOException {
        JsonReader reader = new JsonReader(streamReader);
        reader.setLenient(true);
        String cname = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                cname = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        reader.close();
        return new Weather(cname);
    }

}
