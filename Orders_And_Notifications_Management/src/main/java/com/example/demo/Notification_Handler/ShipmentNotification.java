package com.example.demo.Notification_Handler;

import com.example.demo.model.Channel;
import com.example.demo.model.Order;
import com.example.demo.model.User;

import java.util.ArrayList;

public class ShipmentNotification extends Notification{
    private ShipmentContentGenerator contentGenerator;

    public ShipmentNotification(Channel channel,String language) {
        super(channel, language);
    }

    @Override
    public void generateContent(User user, Order order) {
        setSubject("Shipment Order");
        if(getLanguage().equals("Arabic")){
            contentGenerator = new ArabicShipmentContent();
            setContent(contentGenerator.getContent(user , order));
        }
        else if(getLanguage().equals("English")){
            contentGenerator = new EnglishShipmentContent();
            setContent(contentGenerator.getContent(user , order));
        }
    }
}
