/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.trigger;

import javax.persistence.PostPersist;
import net.hft.dbproject.weatherapp.persistence.StatisticPS;

/**
 *
 * @author Jan
 */
public class CounterTrigger {

    @PostPersist
    public void postPersist(Object obj) {
        new StatisticPS().increaseLocationCounter();
        System.out.println("Trigger fired");
    }
}
