package com.example.demo.Repo;

import com.example.demo.model.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class inMemory implements DataBase {
    public static Map<String, User> persons = new HashMap<>();
    public static Map<String, com.example.demo.model.Order> Orders = new HashMap<>();
    public static Map<String, com.example.demo.model.Order> shippingOrders = new HashMap<>();
    public static Map<String, Product> Products = new HashMap<>();
    public static Map<String, com.example.demo.model.Category> Categories = new HashMap<>();

}
