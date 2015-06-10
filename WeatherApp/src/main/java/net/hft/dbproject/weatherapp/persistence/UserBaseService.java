/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.persistence;

import net.hft.dbproject.weatherapp.entities.AppUser;
import net.hft.dbproject.weatherapp.entities.UserBase;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;

/**
 * Interface for the UserService
 *
 * @author Jan
 */
public interface UserBaseService {

    public void saveNewUser(UserBase user);

    public UserBase getUserByName(String username);

    public void updatePasswortByUserId(long userid, String newPassword);

    public void deleteUser(long userid);
    
    public void addNewWeatherInfoToUser(UserBase user, WeatherInformation info);

}
