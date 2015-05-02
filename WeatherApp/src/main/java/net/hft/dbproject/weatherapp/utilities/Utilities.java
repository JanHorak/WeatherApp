/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jan
 */
public abstract class Utilities {

    public static byte[] getBytesOfFile(String filePath) {
        byte[] restult = null;
        filePath = filePath.substring(6, filePath.length());
        File f = new File(filePath);
        try {
            restult = Files.readAllBytes(f.toPath());
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }

        return restult;
    }
    
    public static double toFahrenheit(double celcius){
        return (celcius * 1.8) + 32.0;
    }
      
    
    public static double toCelsius(double fahrenheit){
        return (fahrenheit - 32.0) * (5.0/9.0);
    }
}
