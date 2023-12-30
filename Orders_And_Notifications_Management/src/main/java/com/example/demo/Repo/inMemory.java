package com.example.demo.Repo;

import com.example.demo.model.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class inMemory implements DataBase {
    public static Map<String, com.example.demo.model.Category> Categories = new HashMap<>(){
        {
            put("Fruits",new Category("Fruits",1));
            put("vegetables",new Category("vegetables",2));
            put("Dairy",new Category("Dairy",1));
            put("Grains",new Category("Grains",0));
            put("Beverages",new Category("Beverages",0));
        }
    };
    public static Map<String, User> persons = new HashMap<>();
    public static Map<String, com.example.demo.model.Order> Orders = new HashMap<>();
    public static Map<String, com.example.demo.model.Order> shippingOrders = new HashMap<>();
    public static Map<String, Product> Products = new HashMap<>(){
        {
            put(
                    "10",
                    new Product("cola", "10", "VendorA", inMemory.Categories.get("Beverages"), 100)
            );
            put(
                    "11",
                    new Product("pepsi", "11", "VendorA", inMemory.Categories.get("Beverages"), 110)
            );
            put(
                    "12",
                    new Product("cake", "12", "VendorA", inMemory.Categories.get("Fruits"), 120)
            );
            put(
                    "13",
                    new Product("hohos", "13", "VendorA", inMemory.Categories.get("Dairy"), 130)
            );
        }
    };



}