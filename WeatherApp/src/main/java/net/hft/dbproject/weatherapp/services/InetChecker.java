package net.hft.dbproject.weatherapp.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 *
 * @author Jan
 */
public abstract class InetChecker {
    
    public static boolean isInternetReachable() {

        URL url = null;
        HttpURLConnection urlConnect = null;
        int resultCode = 404;
        try {
            url = new URL("http://www.google.com");
            urlConnect = (HttpURLConnection) url.openConnection();
            resultCode = urlConnect.getResponseCode();
        } catch (MalformedURLException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        if (resultCode == 200) {
            return true;
        } else {
            return false;
        }

    }

}
