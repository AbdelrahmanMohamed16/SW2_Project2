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
    public static Map<String, com.example.demo.model.Category> Categories = new HashMap<>(){
        {
            put("Fruits",new Category("Fruits",0));
            put("vegitables",new Category("vegitables",0));
            put("Dairy",new Category("Dairy",0));
            put("Grains",new Category("Grains",0));
            put("Beverages",new Category("Beverages",0));
        }
    };
    public static Map<String, Integer> mostUsedEmail=new HashMap<>(){
        {
            put("Ahmed",3);
            put("Mohamed",6);
            put("ALi",4);
            put("khaled",2);
        }
    };
    public static Map<String, Integer> mostUsedPhone=new HashMap<>(){
        {
            put("Ahmed",3);
            put("Mohamed",6);
            put("ALi",4);
            put("khaled",2);
        }
    };
    public static Map<String, Integer> mostUsedPhoneAndEmail=new HashMap<>(){
        {
            put("Ahmed",3);
            put("Mohamed",6);
            put("ALi",4);
            put("khaled",2);
        }
    };




}