package com.example.demo.Repo;

import com.example.demo.Notification_Handler.Notification;
import com.example.demo.model.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class inMemory implements DataBase {
    public static Queue<Notification> Notifications = new LinkedList<>();
    public static Map<String, com.example.demo.model.Category> Categories = new HashMap<>(){
        {
            put("Fruits",new Category("Fruits",10));
            put("vegetables",new Category("vegetables",0));
            put("Dairy",new Category("Dairy",10));
            put("Grains",new Category("Grains",0));
            put("Beverages",new Category("Beverages",20));
        }
    };
    public static Map<String, User> persons = new HashMap<>();
    public static Map<String, com.example.demo.model.Order> Orders = new HashMap<>();
    public static Map<String, com.example.demo.model.Order> shippingOrders = new HashMap<>();
    public static Map<String, Product> Products = new HashMap<>(){
        {
            put(
                    "10",
                    new Product("cola", "10", "VendorA", inMemory.Categories.get("Beverages"), 100,10)
            );
            put(
                    "11",
                    new Product("pepsi", "11", "VendorA", inMemory.Categories.get("Beverages"), 110,10)
            );
            put(
                    "12",
                    new Product("cake", "12", "VendorA", inMemory.Categories.get("Fruits"), 120,10)
            );
            put(
                    "13",
                    new Product("hohos", "13", "VendorA", inMemory.Categories.get("Dairy"), 130,10)
            );
        }
    };

    public static Map<String, Integer> mostUsedPhoneAndEmail = new HashMap<>();
    public static Map<String, Integer> MostUsedTemplate = new HashMap<>(){
        {
            put(
                    "SHIPPING",0
            );
            put(
                    "PLACED",0
            );
        }
    };





}