/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 * Baseclass for the connectionmanagement.
 *
 * @author Jan
 */
public class DataAccess {

    public EntityManager em;

    private EntityManagerFactory emf;

    public void setup() {
        emf = Persistence.createEntityManagerFactory("test");
        em = emf.createEntityManager();
    }

    public void shutDown() {
        if (emf.isOpen()) {
            emf.close();
        }
        if (em.isOpen()) {
            em.close();
        }
    }

    public void openConnection() {
        if (em == null) {
            throw new IllegalStateException("EntityManager is not initilized. Is setup() called?");
        }
        if (em.getTransaction().isActive()) {
            throw new PersistenceException("Connection already active");
        }
        em.getTransaction().begin();
    }

    public void commitStatement() {
        if (!em.isOpen()) {
            throw new PersistenceException("Connection is closed");
        } else if (!em.getTransaction().isActive()) {
            throw new PersistenceException("No Transaction is active");
        }
        em.getTransaction().commit();
    }

}
