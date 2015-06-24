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

    private static final Map<Class, Initializable> CONTAINER = new HashMap<>();

    public static void addController(Class clazz, Initializable instance) {
        int old = CONTAINER.size();
        CONTAINER.put(clazz, instance);
        if (old != CONTAINER.size()) {
            LOGGER.info("Controller added to ControllerContainer: {} | {}", clazz, instance);
        } else {
            LOGGER.info("Controller is still present in ControllerContainer: {} | {}", clazz, instance);
        }

    }

    public static Initializable getController(Class clazz) {
        return CONTAINER.get(clazz);
    }

    public static void removeController(Class clazz) {
        CONTAINER.remove(clazz);
        LOGGER.info("Controller removed from ControllerContainer: {}", clazz);
    }

}
