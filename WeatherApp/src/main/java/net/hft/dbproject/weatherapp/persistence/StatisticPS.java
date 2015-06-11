/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.persistence;

/**
 *
 * @author Jan
 */
public class StatisticPS extends DataAccess {

    public void increaseLocationCounter() {
        setup();
        openConnection();
        em.createNamedQuery("AppStatistic.increaseNOS").executeUpdate();
        commitStatement();
        setup();
    }

}
