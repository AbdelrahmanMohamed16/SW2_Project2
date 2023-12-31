package com.example.demo.Notification_Handler;

import com.example.demo.model.Order;
import com.example.demo.model.User;

public class EnglishShipmentContent implements ShipmentContentGenerator{
    @Override
    public String getContent(User user , Order order) {
        return "Dear "+user.Name+" your order : "+order.getID()+" is on its wa for you.... thanks for using our store";
    }
}
