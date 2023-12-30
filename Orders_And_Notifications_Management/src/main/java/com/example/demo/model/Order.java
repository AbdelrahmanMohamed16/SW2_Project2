package com.example.demo.model ;

import java.util.ArrayList;

public abstract class  Order {
    public String ID ;
    abstract public double calcCost();
    abstract public boolean addOrder(Order o);
    abstract public boolean removeOrder(String oID);
    abstract public ArrayList<Product> getProducts();
    public String getID() {return this.ID;}

}
