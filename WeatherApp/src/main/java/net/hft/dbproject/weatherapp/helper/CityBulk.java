/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.helper;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jan
 */
public class CityBulk {

    private static CityBulk instance;

    private JSONConvertObject[] cities;

    private CityBulk() {
        cities = loadCitiesToMemory();
    }

    public static CityBulk getInstance() {
        if (instance == null) {
            instance = new CityBulk();
            return instance;
        } else {
            return instance;
        }
    }

    public JSONConvertObject[] getCities() {
        return instance.cities;
    }

    private JSONConvertObject[] loadCitiesToMemory() {
        File f = null;
        JSONConvertObject[] obj = null;
        Gson gson = new Gson();
        InputStream in = getClass().getResourceAsStream("/cityBulk/CityBulk.json");
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File("Test.json"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CityBulk.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bytes = new byte[2000000];
        int read = 0;
        try {
            while ((read = in.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException ex) {
            Logger.getLogger(CityBulk.class.getName()).log(Level.SEVERE, null, ex);
        }
        File file = new File("Test.json");
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(file));
            obj = gson.fromJson(br, JSONConvertObject[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.delete();
        return obj;
    }

}
