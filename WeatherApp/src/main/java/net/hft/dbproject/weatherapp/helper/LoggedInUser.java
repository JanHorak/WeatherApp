/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.helper;

import net.hft.dbproject.weatherapp.entities.AppUser;

/**
 *
 * @author AVATSP
 */
public abstract class LoggedInUser {

    private static AppUser currentuser = null;

    
    private LoggedInUser() {
    }

    public static AppUser getInstance() {
        if (currentuser == null) {
            currentuser = new AppUser();
        }
        return currentuser;
    }

}
