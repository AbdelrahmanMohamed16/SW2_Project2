package com.example.demo.Notification_Handler;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.model.User;

public class EnglishPlacementContent implements PlacementContentGenerator{
    @Override
    public String getContent(User user , Order order) {
        String text = "Dear "+user.Name+" your booking of the ";
        for(Product p : order.getProducts()){
            text += p.getName() + " , ";
        }
        text += " is confirmed. thanks for using our store";
        return text;
    }
}
