package com.example.demo.service.User;

import com.example.demo.Notification_Handler.Notification;
import com.example.demo.Notification_Handler.PlacementNotification;
import com.example.demo.Notification_Handler.ShipmentNotification;
import com.example.demo.Repo.inMemory;
import com.example.demo.model.SMS;
import com.example.demo.model.User;
import com.example.demo.model.*;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService implements IuserService {
    public void SendPlacingNotifications(simpleOrder O){
        User u = inMemory.persons.get(O.Customer);
        Channel ch ;
        if(u.channel.equalsIgnoreCase("SMS")){
            ch = new SMS();
            ch.setReceiver(u.mobileNumber);
            inMemory.mostUsedPhoneAndEmail.compute(u.mobileNumber,(key, value) -> (value == null) ? 1 : value + 1);
        }
        else{
            ch = new Email();
            ch.setReceiver(u.Email);
            inMemory.mostUsedPhoneAndEmail.compute(u.Email,(key, value) -> (value == null) ? 1 : value + 1);
        }
        // BUILD Notification
        Notification notification = new PlacementNotification(ch,u.Language);
        notification.generateContent(u,O);
        notification.send();
        inMemory.Notifications.add(notification);
        /////////////////////////////////////////

    }
    public void SendPlacingNotifications(compoundOrder O){
        for (int i = 0; i < O.Orders.size(); i++) {
            SendPlacingNotifications(O.Orders.get(i));
        }

    }
    ////////////////////////////////////////////////////////
    public void SendShippingNotifications(simpleOrder O){
        User u = inMemory.persons.get(O.Customer);
        Channel ch ;
        if(u.channel.equalsIgnoreCase("SMS")){
            ch = new SMS();
            ch.setReceiver(u.mobileNumber);
            inMemory.mostUsedPhoneAndEmail.compute(u.mobileNumber,(key, value) -> (value == null) ? 1 : value + 1);
        }
        else{
            ch = new Email();
            ch.setReceiver(u.Email);
            inMemory.mostUsedPhoneAndEmail.compute(u.Email,(key, value) -> (value == null) ? 1 : value + 1);
        }
        // BUILD Notification
        Notification notification = new ShipmentNotification(ch,u.Language);
        notification.generateContent(u,O);
        notification.send();
        inMemory.Notifications.add(notification);
        /////////////////////////////////////////
    }

    public void SendShippingNotifications(compoundOrder O){
        for (int i = 0; i < O.Orders.size(); i++) {
            SendShippingNotifications(O.Orders.get(i));
        }

    }
    public boolean addUser(User user){
        try{
            if(inMemory.persons.get(user.Email) != null){
                return false;
            }
            else{
                inMemory.persons.put(user.Email , user);
                return true;
            }
        }catch (Exception e){
            System.out.println("Exception in addUser as" + e.getMessage());
            return false;
        }
    }
    public User checkUserExist(String email){
        try {
            return inMemory.persons.get(email);
        } catch (Exception e) {
            System.out.println("Exception in checkUserExist as" + e.getMessage());
        }
        return null;
    }
    public Map<String, User> getAllUsers(){
        return inMemory.persons;
    }
}
