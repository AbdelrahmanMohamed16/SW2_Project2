package com.example.demo.model ;

import java.util.ArrayList;
import java.util.Date;

public abstract class  Order {
    public String ID ;
    public Long shipmentDate;
    public double Cost = 0;
    // TODO: This Feature
    public double shippingFees = 0;


    abstract public double calcCost();
    abstract public boolean addOrder(Order o);
    abstract public boolean removeOrder(String oID);
    abstract public ArrayList<Product> getProducts();
    abstract public void deductCost(double Money);
    abstract public void refundCost(double Money);
    public double getCost() {return this.Cost;}
    public double getshippingFees() {return this.shippingFees;}
    public String getID() {return this.ID;}


}
