package com.example.demo.model ;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;
import java.util.Date;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
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
    //public double getCost() {return this.Cost;}
   // public double getshippingFees() {return this.shippingFees;}
    //public String getID() {return this.ID;}


}
