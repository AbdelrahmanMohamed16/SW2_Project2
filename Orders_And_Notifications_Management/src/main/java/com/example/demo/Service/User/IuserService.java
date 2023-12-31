package com.example.demo.service.User;

import com.example.demo.Notification_Handler.Notification;
import com.example.demo.Notification_Handler.PlacementNotification;
import com.example.demo.Notification_Handler.ShipmentNotification;
import com.example.demo.Repo.inMemory;
import com.example.demo.model.*;

import java.util.Map;

public interface IuserService {
    public void SendPlacingNotifications(simpleOrder O);
    public void SendPlacingNotifications(compoundOrder O);
    public void SendShippingNotifications(simpleOrder O);

    public void SendShippingNotifications(compoundOrder O);
    public boolean addUser(User user);
    public User checkUserExist(String email);
    public Map<String, User> getAllUsers();
}
