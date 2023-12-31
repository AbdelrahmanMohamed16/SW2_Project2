package com.example.demo.Notification_Handler;

import com.example.demo.model.Order;
import com.example.demo.model.User;

public interface PlacementContentGenerator {
    public String getContent (User user , Order order);
}
