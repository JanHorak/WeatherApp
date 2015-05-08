package net.hft.dbproject.weatherapp.manager;

import java.util.HashMap;
import java.util.Map;
import javafx.fxml.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jan
 */
public abstract class ControllerContainer {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerContainer.class);

    private static Map<Class, Initializable> container = new HashMap<>();

    public static void addController(Class clazz, Initializable instance) {
        container.put(clazz, instance);
        LOGGER.info("Controller added to ControllerContainer: {} | {}", clazz, instance);
    }

    public static Initializable getController(Class clazz) {
        return container.get(clazz);
    }

    public static void removeController(Class clazz) {
        container.remove(clazz);
        LOGGER.info("Controller removed from ControllerContainer: {}", clazz);
    }

}
