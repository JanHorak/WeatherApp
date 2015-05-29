package net.hft.dbproject.weatherapp.services;

import static java.lang.Thread.sleep;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jan
 */
public class InetHeartBeat {

    private final static Logger LOGGER = LoggerFactory.getLogger(InetHeartBeat.class);
    private ImageView connectionImage;
    private final long HEARTBEAT_TIME = 60000; // -> One minute
    private final Image IMAGE_OFFLINE = new Image(getClass().getResource("/images/UI/offline-icon.png").toString());
    private final Image IMAGE_ONLINE = new Image(getClass().getResource("/images/UI/online-icon.png").toString());
    private static boolean onlineStatus = false;

    public InetHeartBeat(ImageView image) {
        this.connectionImage = image;
    }

    Task task = new Task<Void>() {
        @Override
        public Void call() {
            LOGGER.info("HeatBeatThread started successfully. HeartBeat every: {} ms", HEARTBEAT_TIME);
            while (true) {
                if (InetChecker.isInternetReachable()) {
                    setStatusReachable();
                } else {
                    setStatusUnreachable();
                }
                try {
                    sleep(HEARTBEAT_TIME);
                } catch (InterruptedException ex) {
                    LOGGER.error("HeatBeatThread is going wrong... \n {}", ex);
                }
            }
        }
    };

    public void startHeartBeat() {
        new Thread(task).start();
    }
    
    public static boolean isOnline(){
        return onlineStatus;
    }

    private void setStatusUnreachable() {
        this.connectionImage.setImage(IMAGE_OFFLINE);
        onlineStatus = false;
        LOGGER.warn("You are currently offline");
    }

    private void setStatusReachable() {
        this.connectionImage.setImage(IMAGE_ONLINE);
        onlineStatus = true;
    }

}
