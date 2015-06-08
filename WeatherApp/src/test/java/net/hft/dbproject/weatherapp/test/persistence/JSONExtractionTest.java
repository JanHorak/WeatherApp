/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.test.persistence;

import java.util.List;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;
import net.hft.dbproject.weatherapp.helper.CityBulk;
import net.hft.dbproject.weatherapp.helper.JSONConvertObject;
import net.hft.dbproject.weatherapp.services.Locationfinder;
import net.hft.dbproject.weatherapp.services.OfflineLocationFinder;
import net.hft.dbproject.weatherapp.services.WeatherAPIConnection;
import static org.hamcrest.core.Is.is;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Jan
 */
public class JSONExtractionTest {

    @Test
    public void shouldExtractAllHelperObjectsFromJSON() {
        JSONConvertObject[] s = CityBulk.getInstance().getCities();
        Locationfinder lf = new OfflineLocationFinder();
        Assert.assertThat(lf.getLocationsByCityName("Stuttgart").size(), is(7));
    }

    @Test
    public void shouldReturnWeatherFromService() {
        List<WeatherInformation> d = WeatherAPIConnection.requestCitiesByLike("Berlin");
        Assert.assertThat(d.size(), is(10));

    }

    @Test
    public void shouldReturnWeatherFromService2() {
        WeatherInformation d = WeatherAPIConnection.requestCityByID(2825297);
        System.out.println(d.getCityName());

    }

}
