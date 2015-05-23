/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Jan
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "AppUser.findByID", query = "SELECT u FROM AppUser u WHERE u.id = :id"),
        @NamedQuery(name="AppUser.fingByNAME", query="SELECT u FROM AppUser u WHERE u.name =:name")
})
public class AppUser extends UserBase implements Serializable {

}
