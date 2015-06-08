package net.hft.dbproject.weatherapp.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import net.hft.dbproject.weatherapp.helper.PropertiesHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jan
 */
public class PropertiesService extends PropertiesHelper {

    private static Properties properties;
    private static final File INIFILE = new File(getShortIniPath());
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesService.class);

    public PropertiesService() {
        loadProperties();
    }

    public void storeCityAndId(String name, int id) {
        if (isPropertiesFileExisting()) {
            properties.setProperty("name", name);
            properties.setProperty("ident", String.valueOf(id));
            try {
                properties.store(new FileOutputStream(INIFILE), "WEATHERAPP- PROPERTIESFILE");
                LOGGER.info("New values stored: {}, {}", name, id);
            } catch (FileNotFoundException ex) {
                LOGGER.error(ex.toString());
            } catch (IOException ex) {
                LOGGER.error(ex.toString());
            }
        }
    }
    
    public void storeCalculation(String calc) {
        if (isPropertiesFileExisting()) {
            properties.setProperty("calc", calc);
            try {
                properties.store(new FileOutputStream(INIFILE), "WEATHERAPP- PROPERTIESFILE");
                LOGGER.info("New values stored: {}", calc);
            } catch (FileNotFoundException ex) {
                LOGGER.error(ex.toString());
            } catch (IOException ex) {
                LOGGER.error(ex.toString());
            }
        }
    }

    private void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileInputStream(INIFILE));
            } catch (IOException ex) {
                LOGGER.error(ex.toString());
            }
        }
    }

    public String getName() {
        return properties.getProperty("name");
    }

    public String getIdentCode() {
        return properties.getProperty("ident");
    }
    
    public String getCalculation() {
        return properties.getProperty("calc");
    }

}
