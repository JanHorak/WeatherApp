/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.uiactions;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.hft.dbproject.weatherapp.controller.ChangepasswordController;
import net.hft.dbproject.weatherapp.enums.CSSFile;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.manager.Stagemanager;

/**
 *
 * @author AVATSP
 */
public class Changepasswordactions {
    
    
    ChangepasswordController controller;
public Changepasswordactions(){
     
    this.controller  = (ChangepasswordController) ControllerContainer.getController(ChangepasswordController.class);
}


    
}
