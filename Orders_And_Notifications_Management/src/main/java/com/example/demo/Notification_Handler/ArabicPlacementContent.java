package com.example.demo.Notification_Handler;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.model.User;

public class ArabicPlacementContent implements PlacementContentGenerator{
    @Override
    public String getContent(User user , Order order) {
        String text = "عزيزي "+user.Name+" تم تأكيد حجزك للمنتج ";
        for(Product p : order.getProducts()){
            text += p.getName() + " , ";
        }
        text += " شكرا لأستخدام متجرنا";
        return text;
    }
}
