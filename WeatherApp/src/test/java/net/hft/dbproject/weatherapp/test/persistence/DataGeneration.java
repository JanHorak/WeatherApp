/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.test.persistence;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.hft.dbproject.weatherapp.entities.WeatherImage;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;
import net.hft.dbproject.weatherapp.utilities.Utilities;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jan
 */
public class DataGeneration {

    // Dayvalues
    private byte[] clearSkyD;
    private byte[] fewCloudsD;
    private byte[] scatteredCloudsD;
    private byte[] brokenCloudsD;
    private byte[] showerRainD;
    private byte[] rainD;
    private byte[] thunderstormD;
    private byte[] snowD;
    private byte[] mistD;
    private byte[] freezingRainD;

    //Nightvalues
    private byte[] clearSkyN;
    private byte[] fewCloudsN;
    private byte[] scatteredCloudsN;
    private byte[] brokenCloudsN;
    private byte[] showerRainN;
    private byte[] rainN;
    private byte[] thunderstormN;
    private byte[] snowN;
    private byte[] mistN;
    private byte[] freezingRainN;

    @Before
    public void loadImages() {

        // DayData
        clearSkyD = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-clear-icon.png").toString());
        fewCloudsD = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-clouds-icon.png").toString());
        scatteredCloudsD = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-many-clouds-icon.png").toString());
        brokenCloudsD = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-many-clouds-icon.png").toString());
        showerRainD = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-showers-icon.png").toString());
        rainD = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-showers-scattered-icon.png").toString());
        thunderstormD = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-storm-icon.png").toString());
        snowD = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-snow-scattered-icon.png").toString());
        mistD = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-mist.png").toString());
        freezingRainD = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-snow-rain-icon.png").toString());

        //NightData
        clearSkyN = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-clear-night-icon.png").toString());
        fewCloudsN = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-clouds-night-icon.png").toString());
        scatteredCloudsN = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-many-clouds-icon.png").toString());
        brokenCloudsN = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-many-clouds-icon.png").toString());
        showerRainN = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-showers-night-icon.png").toString());
        rainN = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-showers-scattered-night-icon.png").toString());
        thunderstormN = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-storm-night-icon.png").toString());
        snowN = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-snow-scattered-night-icon.png").toString());
        mistN = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-mist.png").toString());
        freezingRainN = Utilities.getBytesOfFile(getClass().getResource("/images/weather/Status-weather-snow-rain-icon.png").toString());
    }

    @Test
    public void createWeatherImageData() {
        // <editor-fold defaultstate="collapsed" desc="Thunderimages">
        WeatherImage thunderLightRainDay = new WeatherImage();
        thunderLightRainDay.setIconId(200);
        thunderLightRainDay.setImagedataDay(thunderstormD);
        thunderLightRainDay.setImagedataNight(thunderstormN);
        thunderLightRainDay.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage thunderRainDay = new WeatherImage();
        thunderRainDay.setIconId(201);
        thunderRainDay.setImagedataDay(thunderstormD);
        thunderRainDay.setImagedataNight(thunderstormN);
        thunderRainDay.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage thunderHeavyRainDay = new WeatherImage();
        thunderHeavyRainDay.setIconId(202);
        thunderHeavyRainDay.setImagedataDay(thunderstormD);
        thunderHeavyRainDay.setImagedataNight(thunderstormN);
        thunderHeavyRainDay.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage thunderstormLightDay = new WeatherImage();
        thunderstormLightDay.setIconId(210);
        thunderstormLightDay.setImagedataDay(thunderstormD);
        thunderstormLightDay.setImagedataNight(thunderstormN);
        thunderstormLightDay.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage thunderstormDay = new WeatherImage();
        thunderstormDay.setIconId(211);
        thunderstormDay.setImagedataDay(thunderstormD);
        thunderstormDay.setImagedataNight(thunderstormN);
        thunderstormDay.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage thunderstormHeavyDay = new WeatherImage();
        thunderstormHeavyDay.setIconId(212);
        thunderstormHeavyDay.setImagedataDay(thunderstormD);
        thunderstormHeavyDay.setImagedataNight(thunderstormN);
        thunderstormHeavyDay.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage thunderstormRaggedDay = new WeatherImage();
        thunderstormRaggedDay.setIconId(221);
        thunderstormRaggedDay.setImagedataDay(thunderstormD);
        thunderstormRaggedDay.setImagedataNight(thunderstormN);
        thunderstormRaggedDay.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage thunderstormDrizzleLightDay = new WeatherImage();
        thunderstormDrizzleLightDay.setIconId(230);
        thunderstormDrizzleLightDay.setImagedataDay(thunderstormD);
        thunderstormDrizzleLightDay.setImagedataNight(thunderstormN);
        thunderstormDrizzleLightDay.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage thunderstormDrizzleDay = new WeatherImage();
        thunderstormDrizzleDay.setIconId(231);
        thunderstormDrizzleDay.setImagedataDay(thunderstormD);
        thunderstormDrizzleDay.setImagedataNight(thunderstormN);
        thunderstormDrizzleDay.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage thunderstormHeavyRaggedDay = new WeatherImage();
        thunderstormHeavyRaggedDay.setIconId(232);
        thunderstormHeavyRaggedDay.setImagedataDay(thunderstormD);
        thunderstormHeavyRaggedDay.setImagedataNight(thunderstormN);
        thunderstormHeavyRaggedDay.setInfo(new ArrayList<WeatherInformation>());
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Drizzle">
        WeatherImage drizzleLight = new WeatherImage();
        drizzleLight.setIconId(300);
        drizzleLight.setImagedataDay(rainD);
        drizzleLight.setImagedataNight(rainN);
        drizzleLight.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage drizzle = new WeatherImage();
        drizzle.setIconId(301);
        drizzle.setImagedataDay(rainD);
        drizzle.setImagedataNight(rainN);
        drizzle.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage heavyDrizzle = new WeatherImage();
        heavyDrizzle.setIconId(302);
        heavyDrizzle.setImagedataDay(rainD);
        heavyDrizzle.setImagedataNight(rainN);
        heavyDrizzle.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage lightDrizzle = new WeatherImage();
        lightDrizzle.setIconId(310);
        lightDrizzle.setImagedataDay(rainD);
        lightDrizzle.setImagedataNight(rainN);
        lightDrizzle.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage drizzleRain = new WeatherImage();
        drizzleRain.setIconId(311);
        drizzleRain.setImagedataDay(rainD);
        drizzleRain.setImagedataNight(rainN);
        drizzleRain.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage heavyDrizzleRain = new WeatherImage();
        heavyDrizzleRain.setIconId(312);
        heavyDrizzleRain.setImagedataDay(rainD);
        heavyDrizzleRain.setImagedataNight(rainN);
        heavyDrizzleRain.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage showerRainAndDrizzle = new WeatherImage();
        showerRainAndDrizzle.setIconId(313);
        showerRainAndDrizzle.setImagedataDay(rainD);
        showerRainAndDrizzle.setImagedataNight(rainN);
        showerRainAndDrizzle.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage heavyShowerRainAndDrizzle = new WeatherImage();
        heavyShowerRainAndDrizzle.setIconId(314);
        heavyShowerRainAndDrizzle.setImagedataDay(rainD);
        heavyShowerRainAndDrizzle.setImagedataNight(rainN);
        heavyShowerRainAndDrizzle.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage showerDrizzle = new WeatherImage();
        showerDrizzle.setIconId(321);
        showerDrizzle.setImagedataDay(rainD);
        showerDrizzle.setImagedataNight(rainN);
        showerDrizzle.setInfo(new ArrayList<WeatherInformation>());
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Rain">
        WeatherImage lightRain = new WeatherImage();
        lightRain.setIconId(500);
        lightRain.setImagedataDay(showerRainD);
        lightRain.setImagedataNight(showerRainN);
        lightRain.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage heavyRain = new WeatherImage();
        heavyRain.setIconId(502);
        heavyRain.setImagedataDay(showerRainD);
        heavyRain.setImagedataNight(showerRainN);
        heavyRain.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage moderateRain = new WeatherImage();
        moderateRain.setIconId(501);
        moderateRain.setImagedataDay(showerRainD);
        moderateRain.setImagedataNight(showerRainN);
        moderateRain.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage veryHeavyRain = new WeatherImage();
        veryHeavyRain.setIconId(503);
        veryHeavyRain.setImagedataDay(showerRainD);
        veryHeavyRain.setImagedataNight(showerRainN);
        veryHeavyRain.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage extremeRain = new WeatherImage();
        extremeRain.setIconId(504);
        extremeRain.setImagedataDay(showerRainD);
        extremeRain.setImagedataNight(showerRainN);
        extremeRain.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage freezingRain = new WeatherImage();
        freezingRain.setIconId(511);
        freezingRain.setImagedataDay(freezingRainD);
        freezingRain.setImagedataNight(freezingRainN);
        freezingRain.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage lightintensRain = new WeatherImage();
        lightintensRain.setIconId(520);
        lightintensRain.setImagedataDay(rainD);
        lightintensRain.setImagedataNight(rainN);
        lightintensRain.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage showerRain = new WeatherImage();
        showerRain.setIconId(521);
        showerRain.setImagedataDay(rainD);
        showerRain.setImagedataNight(rainN);
        showerRain.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage heavyIntensShowerRain = new WeatherImage();
        heavyIntensShowerRain.setIconId(522);
        heavyIntensShowerRain.setImagedataDay(rainD);
        heavyIntensShowerRain.setImagedataNight(rainN);
        heavyIntensShowerRain.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage raggedShowerRain = new WeatherImage();
        raggedShowerRain.setIconId(531);
        raggedShowerRain.setImagedataDay(rainD);
        raggedShowerRain.setImagedataNight(rainN);
        raggedShowerRain.setInfo(new ArrayList<WeatherInformation>());
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Snow">
        WeatherImage lightSnow = new WeatherImage();
        lightSnow.setIconId(600);
        lightSnow.setImagedataDay(snowD);
        lightSnow.setImagedataNight(snowN);
        lightSnow.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage snow = new WeatherImage();
        snow.setIconId(601);
        snow.setImagedataDay(snowD);
        snow.setImagedataNight(snowN);
        snow.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage heavySnow = new WeatherImage();
        heavySnow.setIconId(602);
        heavySnow.setImagedataDay(snowD);
        heavySnow.setImagedataNight(snowN);
        heavySnow.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage sleet = new WeatherImage();
        sleet.setIconId(611);
        sleet.setImagedataDay(snowD);
        sleet.setImagedataNight(snowN);
        sleet.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage showerSleet = new WeatherImage();
        showerSleet.setIconId(612);
        showerSleet.setImagedataDay(snowD);
        showerSleet.setImagedataNight(snowN);
        showerSleet.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage lightRainAndShower = new WeatherImage();
        lightRainAndShower.setIconId(615);
        lightRainAndShower.setImagedataDay(snowD);
        lightRainAndShower.setImagedataNight(snowN);
        lightRainAndShower.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage rainAndSnow = new WeatherImage();
        rainAndSnow.setIconId(616);
        rainAndSnow.setImagedataDay(snowD);
        rainAndSnow.setImagedataNight(snowN);
        rainAndSnow.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage lightShowerAndSnow = new WeatherImage();
        lightShowerAndSnow.setIconId(620);
        lightShowerAndSnow.setImagedataDay(snowD);
        lightShowerAndSnow.setImagedataNight(snowN);
        lightShowerAndSnow.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage showerSnow = new WeatherImage();
        showerSnow.setIconId(621);
        showerSnow.setImagedataDay(snowD);
        showerSnow.setImagedataNight(snowN);
        showerSnow.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage heavyShowerSnow = new WeatherImage();
        heavyShowerSnow.setIconId(622);
        heavyShowerSnow.setImagedataDay(snowD);
        heavyShowerSnow.setImagedataNight(snowN);
        heavyShowerSnow.setInfo(new ArrayList<WeatherInformation>());
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="At">
        WeatherImage mist = new WeatherImage();
        mist.setIconId(701);
        mist.setImagedataDay(mistD);
        mist.setImagedataNight(mistN);
        mist.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage smoke = new WeatherImage();
        smoke.setIconId(711);
        smoke.setImagedataDay(mistD);
        smoke.setImagedataNight(mistN);
        smoke.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage haze = new WeatherImage();
        haze.setIconId(721);
        haze.setImagedataDay(mistD);
        haze.setImagedataNight(mistN);
        haze.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage sandDust = new WeatherImage();
        sandDust.setIconId(731);
        sandDust.setImagedataDay(mistD);
        sandDust.setImagedataNight(mistN);
        sandDust.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage fog = new WeatherImage();
        fog.setIconId(741);
        fog.setImagedataDay(mistD);
        fog.setImagedataNight(mistN);
        fog.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage dust = new WeatherImage();
        dust.setIconId(761);
        dust.setImagedataDay(mistD);
        dust.setImagedataNight(mistN);
        dust.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage sand = new WeatherImage();
        sand.setIconId(751);
        sand.setImagedataDay(mistD);
        sand.setImagedataNight(mistN);
        sand.setInfo(new ArrayList<WeatherInformation>());
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Clouds">
        WeatherImage clearSky = new WeatherImage();
        clearSky.setIconId(800);
        clearSky.setImagedataDay(clearSkyD);
        clearSky.setImagedataNight(clearSkyN);
        clearSky.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage fewClouds = new WeatherImage();
        fewClouds.setIconId(801);
        fewClouds.setImagedataDay(fewCloudsD);
        fewClouds.setImagedataNight(fewCloudsN);
        fewClouds.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage scatteredClouds = new WeatherImage();
        scatteredClouds.setIconId(802);
        scatteredClouds.setImagedataDay(scatteredCloudsD);
        scatteredClouds.setImagedataNight(scatteredCloudsN);
        scatteredClouds.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage brokenClouds = new WeatherImage();
        brokenClouds.setIconId(803);
        brokenClouds.setImagedataDay(brokenCloudsD);
        brokenClouds.setImagedataNight(brokenCloudsN);
        brokenClouds.setInfo(new ArrayList<WeatherInformation>());

        WeatherImage overcast = new WeatherImage();
        overcast.setIconId(804);
        overcast.setImagedataDay(brokenCloudsD);
        overcast.setImagedataNight(brokenCloudsN);
        overcast.setInfo(new ArrayList<WeatherInformation>());
        // </editor-fold>

        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("test");
        em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(thunderLightRainDay);
        em.persist(thunderRainDay);
        em.persist(thunderHeavyRainDay);
        em.persist(thunderstormLightDay);
        em.persist(thunderstormDay);
        em.persist(thunderstormHeavyDay);
        em.persist(thunderstormRaggedDay);
        em.persist(thunderstormDrizzleLightDay);
        em.persist(thunderstormDrizzleDay);
        em.persist(thunderstormHeavyRaggedDay);
        em.persist(drizzleLight);
        em.persist(drizzle);
        em.persist(drizzleRain);
        em.persist(heavyDrizzleRain);
        em.persist(showerRainAndDrizzle);
        em.persist(heavyShowerRainAndDrizzle);
        em.persist(showerDrizzle);
        em.persist(lightRain);
        em.persist(heavyRain);
        em.persist(moderateRain);
        em.persist(veryHeavyRain);
        em.persist(extremeRain);
        em.persist(freezingRain);
        em.persist(lightintensRain);
        em.persist(showerRain);
        em.persist(heavyIntensShowerRain);
        em.persist(raggedShowerRain);
        em.persist(lightSnow);
        em.persist(snow);
        em.persist(heavySnow);
        em.persist(sleet);
        em.persist(showerSleet);
        em.persist(lightRainAndShower);
        em.persist(rainAndSnow);
        em.persist(lightShowerAndSnow);
        em.persist(showerSnow);
        em.persist(heavyShowerSnow);
        em.persist(mist);
        em.persist(smoke);
        em.persist(sandDust);
        em.persist(haze);
        em.persist(fog);
        em.persist(dust);
        em.persist(sand);
        em.persist(clearSky);
        em.persist(fewClouds);
        em.persist(scatteredClouds);
        em.persist(brokenClouds);
        em.persist(overcast);
        
        em.getTransaction().commit();
        em.close();

    }

}
