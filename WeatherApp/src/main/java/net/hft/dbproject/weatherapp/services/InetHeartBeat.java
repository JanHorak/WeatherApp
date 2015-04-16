package net.hft.dbproject.weatherapp.services;

import java.util.logging.Level;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.log4j.Logger;

/**
 *
 * @author Jan
 */
public class InetHeartBeat extends Thread {

    private Logger logger;
    private ImageView connectionImage;
    private final long HEARTBEAT_TIME = 60000; // -> One minute
    private final Image IMAGE_OFFLINE = new Image(getClass().getResource("/images/UI/offline-icon.png").toString());
    private final Image IMAGE_ONLINE = new Image(getClass().getResource("/images/UI/online-icon.png").toString());

    public InetHeartBeat(ImageView image){
        this.logger = Logger.getLogger(InetHeartBeat.class);
        this.connectionImage = image;
    }

    @Override
    public void run() {
        logger.info("HeatBeatThread started successfully. HeartBeat every: " + HEARTBEAT_TIME + "ms");
        while (true) {
            if (InetChecker.isInternetReachable()) {
                setStatusReachable();
            } else {
                setStatusUnreachable();
            }
            try {
                sleep(HEARTBEAT_TIME);
            } catch (InterruptedException ex) {
                logger.error("HeatBeatThread is going wrong..." + ex);
            }
        }

    }

    private void setStatusUnreachable() {
        this.connectionImage.setImage(IMAGE_OFFLINE);
        logger.warn("You are currently offline");
    }

    private void setStatusReachable() {
        this.connectionImage.setImage(IMAGE_ONLINE);
    }

}
