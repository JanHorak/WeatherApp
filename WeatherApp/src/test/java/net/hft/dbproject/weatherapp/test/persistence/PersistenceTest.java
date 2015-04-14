/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.test.persistence;

import net.hft.dbproject.weatherapp.entities.AppUser;
import net.hft.dbproject.weatherapp.entities.UserBase;
import net.hft.dbproject.weatherapp.persistence.UserBaseService;
import net.hft.dbproject.weatherapp.persistence.UserService;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Jan
 */
@Ignore
public class PersistenceTest {
    
    @Test
    public void shouldPersistSimpleUser(){
        UserBaseService service = new UserService();
        UserBase user = new AppUser();
        service.saveNewUser(user);
    }
}
