/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.persistence;

import net.hft.dbproject.weatherapp.entities.UserBase;

/**
 *
 * @author Jan
 */
public class UserService extends DataAccess implements UserBaseService {

    @Override
    public void saveNewUser(UserBase user) {
        setup();
        openConnection();
        em.persist(user);
        commitStatement();
        shutDown();
    }
    
}
