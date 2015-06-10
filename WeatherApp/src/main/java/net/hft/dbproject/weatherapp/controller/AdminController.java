/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import net.hft.dbproject.weatherapp.entities.AppUser;
import net.hft.dbproject.weatherapp.entities.Notification;
import net.hft.dbproject.weatherapp.entities.WeatherInformation;
import net.hft.dbproject.weatherapp.manager.ControllerContainer;
import net.hft.dbproject.weatherapp.persistence.AdminFunctions;
import net.hft.dbproject.weatherapp.persistence.AdminManager;
import net.hft.dbproject.weatherapp.uiactions.Adminpageactions;

/**
 *
 * @author Jan
 */
public class AdminController implements Initializable {

    @FXML
    private ComboBox userList;

    private AppUser selectedUser;

    @FXML
    private ListView history;

    @FXML
    private ListView notificationList;

    @FXML
    private TextField cityField;

    @FXML
    private TextField mailAddressField;

    @FXML
    private Button saveNotificationButton;

    @FXML
    private Button deleteNotificationButton;

    private String selectedNotification;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ControllerContainer.addController(AdminController.class, this);
        this.selectedUser = new AppUser();
        loadUsersInComboBox();
        initActions();
    }

    private void initActions() {
        Adminpageactions actions = new Adminpageactions();
        userList.valueProperty().addListener(actions.userListSelection);
        saveNotificationButton.setOnAction(actions.saveNotificationEvent);
        deleteNotificationButton.setOnAction(actions.deleteNotificationEvent);
        
        // TODO
        notificationList.getSelectionModel().selectionModeProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                System.out.println((String) t1);
            }
        });
    }

    private void loadUsersInComboBox() {
        userList.setItems(null);
        AdminFunctions adminManager = new AdminManager();
        List<AppUser> users = new ArrayList<>();
        users = adminManager.findAllAppUsers();
        ObservableList<AppUser> options
                = FXCollections.observableArrayList(users);
        userList.setItems(options);
    }

    public void refreshUsers() {
        AppUser old = selectedUser;
        loadUsersInComboBox();
    }

    public AppUser getSelectedUser() {
        return selectedUser;
    }

    public ComboBox getUserList() {
        return userList;
    }

    public void setSelectedUser(AppUser selectedUser) {
        this.selectedUser = selectedUser;
    }

    public ListView getHistory() {
        return history;
    }

    public void setHistory(ListView history) {
        this.history = history;
    }

    public void updateNotifications(List<Notification> notifications) {
        notificationList.setItems(null);
        List<String> notList = new ArrayList<>();
        for (Notification i : notifications) {
            String entry = i.getTargetLocation() + ", reported to: " + i.getEmailAddress();
            notList.add(entry);
        }
        ObservableList<String> hist
                = FXCollections.observableArrayList(notList);
        notificationList.setItems(hist);
    }

    public void updateHistory(List<WeatherInformation> incoming) {
        history.setItems(null);
        List<String> historyList = new ArrayList<>();
        for (WeatherInformation i : incoming) {
            String entry = i.getCityName() + ",  Ident:" + String.valueOf(i.getCityIdentifier());
            historyList.add(entry);
        }
        ObservableList<String> hist
                = FXCollections.observableArrayList(historyList);
        history.setItems(hist);
    }

    public ListView getNotifications() {
        return notificationList;
    }

    public void setNotifications(ListView notifications) {
        this.notificationList = notifications;
    }

    public TextField getCityField() {
        return cityField;
    }

    public void setCityField(TextField cityField) {
        this.cityField = cityField;
    }

    public TextField getMailAddressField() {
        return mailAddressField;
    }

    public void setMailAddressField(TextField mailAddressField) {
        this.mailAddressField = mailAddressField;
    }

    public Button getDeleteNotificationButton() {
        return deleteNotificationButton;
    }

    public String getSelectedNotification() {
        return selectedNotification;
    }

    public void setSelectedNotification(String selectedNotification) {
        this.selectedNotification = selectedNotification;
    }

}
