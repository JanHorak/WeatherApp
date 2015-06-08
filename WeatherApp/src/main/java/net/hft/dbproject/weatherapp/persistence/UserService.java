/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.persistence;

import javax.persistence.NoResultException;
import net.hft.dbproject.weatherapp.entities.UserBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jan
 */
public class UserService extends DataAccess implements UserBaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Override
    public void saveNewUser(UserBase user) {
        setup();
        openConnection();
        em.persist(user);
        commitStatement();
        shutDown();
    }

    /**
     *
     * @param user
     */
    @Override
    public void updatePasswortByUserId(int userid, String newPassword) {
        setup();
        openConnection();
        em.createNamedQuery("AppUser.updateByPASSWORD").setParameter("newPassword", newPassword).setParameter("id", userid).executeUpdate();
        commitStatement();
        shutDown();
    }


    @Override
    public UserBase getUserByName(String username) {
        UserBase result = null;
        setup();
        openConnection();
        try {
            result = (UserBase) em.createNamedQuery("AppUser.fingByNAME").setParameter("name", username).getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.error("No result found for Query. Exception: {}", ex);
        }
        shutDown();
        return result;
    }

    }
