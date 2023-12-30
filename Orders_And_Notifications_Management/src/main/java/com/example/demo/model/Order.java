package com.example.demo.model;

import java.util.ArrayList;

public interface  Order {
    public double calcCost();
    public boolean addOrder(Order o);
    public boolean removeOrder(String oID);

}
