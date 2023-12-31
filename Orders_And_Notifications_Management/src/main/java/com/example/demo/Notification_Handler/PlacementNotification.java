package com.example.demo.Notification_Handler;

import com.example.demo.model.Channel;
import com.example.demo.model.Order;
import com.example.demo.model.User;

import java.util.ArrayList;

public class PlacementNotification extends Notification{
    private PlacementContentGenerator contentGenerator;
    public PlacementNotification(Channel channel,String language) {
        super(channel, language);
    }

    @Override
    public void generateContent(User user, Order order) {
        setSubject("Placement order");
        if(getLanguage().equals("Arabic")){
            contentGenerator = new ArabicPlacementContent();
            setContent(contentGenerator.getContent(user , order));
        }
        else if(getLanguage().equals("English")){
            contentGenerator = new EnglishPlacementContent();
            setContent(contentGenerator.getContent(user , order));
        }
    }
}
