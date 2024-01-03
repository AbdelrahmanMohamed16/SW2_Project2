package com.example.demo.Notification_Handler;

import com.example.demo.model.Order;
import com.example.demo.model.User;

public class ArabicShipmentContent implements ShipmentContentGenerator{
    @Override
    public String getContent(User user , Order order) {
        return "عزيزي "+user.Name+" طلبك :  "+order.getID()+" في طريقه إليك.... شكرا لأستخدام متجرنا";
    }
}
