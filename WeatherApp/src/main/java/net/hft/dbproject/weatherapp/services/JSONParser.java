/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.services;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import net.hft.dbproject.weatherapp.entities.Temperature;
import net.hft.dbproject.weatherapp.entities.WeatherImage;
import net.hft.dbproject.weatherapp.entities.Location;
import net.hft.dbproject.weatherapp.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jan
 */
public abstract class JSONParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONParser.class);

    private static InputStreamReader queryInputStreamReader;
    private static JsonReader queryJSONReader;
    private static boolean singleObject = false;

    /**
     * Returns the list of {@link Location} depending on the incoming JSON-
     * Stream.
     *
     * @param queryResult
     * @return List of Location
     * @throws IOException
     */
    public static List<Location> toLocationList(InputStream queryResult) throws IOException {

        initialize(queryResult);

        List<Location> result = new ArrayList<Location>();

        queryJSONReader.beginObject();
        while (queryJSONReader.hasNext()) {
            String name = queryJSONReader.nextName();

            if (name.equals("list")) {
                queryJSONReader.beginArray();
                while (queryJSONReader.hasNext()) {
                    queryJSONReader.beginObject();
                    Location currWeather = toLocation();
                    queryJSONReader.endObject();
                    if (currWeather != null) {
                        result.add(currWeather);
                    }
                }
                queryJSONReader.endArray();
            } else if (name.equals("coord")) {
                singleObject = true;
                result.add(toLocation());
            } else {
                queryJSONReader.skipValue();
            }
        }
        queryJSONReader.endObject();

        shutDown();

        return result;
    }

    /**
     * Parsingmethod for the single JSON- Objects.
     *
     * @return Parsed Location
     */
    private static Location toLocation() {

        Location result = new Location();
        String cityName = "";
        Temperature cityTemperature = null;
        double tempAvg = 0.0;
        double tempMin = 0.0;
        double tempMax = 0.0;
        String countryCode = "";
        int imageIconID = 0;
        int ident = 0;

        String weatherDescription = "";

        // GPS- Coordinates
        float lat = 0, lon = 0;

        try {
            int passedCounter = 0;
            while (queryJSONReader.hasNext() && passedCounter < 6) {

                while (passedCounter < 6) {
                    String selectedValue = singleObject ? "coord" : queryJSONReader.nextName();
                    switch (selectedValue) {
                        case "id": {
                            ident = queryJSONReader.nextInt();
                            passedCounter++;
                            break;
                        }
                        case "name": {
                            cityName = queryJSONReader.nextString();
                            passedCounter++;
                            break;
                        }
                        case "coord": {
                            queryJSONReader.beginObject();
                            while (queryJSONReader.hasNext()) {
                                String name2 = queryJSONReader.nextName();
                                if (name2.equals("lon")) {
                                    lon = (float) queryJSONReader.nextDouble();
                                    name2 = queryJSONReader.nextName();
                                }
                                if (name2.equals("lat")) {
                                    lat = (float) queryJSONReader.nextDouble();
                                }
                                queryJSONReader.endObject();
                                singleObject = false;
                                passedCounter++;
                                break;
                            }
                            break;
                        }
                        case "main": {
                            queryJSONReader.beginObject();
                            while (queryJSONReader.hasNext()) {
                                String attribute = queryJSONReader.nextName();
                                if (attribute.equals("temp")) {
                                    tempAvg = Utilities.fromKelvinToFahrenheit(queryJSONReader.nextDouble());
                                } else if (attribute.equals("temp_min")) {
                                    tempMin = Utilities.fromKelvinToFahrenheit(queryJSONReader.nextDouble());
                                } else if (attribute.equals("temp_max")) {
                                    tempMax = Utilities.fromKelvinToFahrenheit(queryJSONReader.nextDouble());
                                } else {
                                    queryJSONReader.skipValue();
                                }
                            }
                            queryJSONReader.endObject();
                            cityTemperature = new Temperature(tempAvg, tempMin, tempMax);
                            passedCounter++;
                            break;
                        }
                        case "sys": {
                            queryJSONReader.beginObject();
                            while (queryJSONReader.hasNext()) {
                                String country = queryJSONReader.nextName();
                                if (country.equals("country")) {
                                    countryCode = queryJSONReader.nextString();
                                } else {
                                    queryJSONReader.skipValue();
                                }
                            }
                            queryJSONReader.endObject();
                            passedCounter++;
                            break;
                        }
                        case "weather": {
                            queryJSONReader.beginArray();
                            queryJSONReader.beginObject();
                            while (queryJSONReader.hasNext()) {
                                String id = queryJSONReader.nextName();
                                if (id.equals("id")) {
                                    imageIconID = queryJSONReader.nextInt();
                                } else {
                                    queryJSONReader.skipValue();
                                }
                            }
                            queryJSONReader.endObject();
                            JsonToken t = queryJSONReader.peek();
                            if (t == JsonToken.BEGIN_OBJECT) {
                                queryJSONReader.beginObject();
                                while (t != JsonToken.END_OBJECT) {
                                    String v = queryJSONReader.nextName();
                                    queryJSONReader.skipValue();
                                    t = queryJSONReader.peek();
                                }
                                queryJSONReader.endObject();
                            }
                            queryJSONReader.endArray();

                            // set this flat to the last element which is requested!
                            passedCounter++;
                            break;
                        }
                        default: {
                            queryJSONReader.skipValue();
                        }
                    }

                }

                result = new Location(ident, cityName, countryCode, cityTemperature, lat, lon);
                WeatherImage wi = new WeatherImage();
                wi.setIconId(imageIconID);
                result.setImage(wi);

            }

        } catch (IOException ex) {
            LOGGER.error(ex.toString());
        }
        return result;

    }

    private static void initialize(InputStream queryResult) {
        queryInputStreamReader = new InputStreamReader(queryResult);
        queryJSONReader = new JsonReader(queryInputStreamReader);
        queryJSONReader.setLenient(true);
        LOGGER.info("JSON- Parsing initialized");
    }

    private static void shutDown() throws IOException {
        queryJSONReader.close();
        queryInputStreamReader.close();
        queryJSONReader = null;
        queryInputStreamReader = null;
        LOGGER.info("JSON- Parsing ended");
    }
}
