/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.helper;

import java.io.File;
import java.io.IOException;
import javax.swing.text.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jan
 */
public abstract class PropertiesHelper {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Utilities.class);
    private static final String PROPERTIESFILEPATH = "../weatherapp.ini";
    
    public static String getShortIniPath(){
        return PROPERTIESFILEPATH;
    }
    
    public static String getFullIniPath(){
        return new File(PROPERTIESFILEPATH).getAbsolutePath();
    }
    
    public static boolean isPropertiesFileExisting(){
        File prop = new File(PROPERTIESFILEPATH);
        if (prop.exists()){
            LOGGER.info("Propertiesfile is found...");
            return true;
        } else {
            try {
                LOGGER.warn("Cannot find Properties. Will be created at {}", prop.getAbsoluteFile());
                prop.createNewFile();
            } catch (IOException ex) {
                
            }
            return false;
        }
    }
    
}
