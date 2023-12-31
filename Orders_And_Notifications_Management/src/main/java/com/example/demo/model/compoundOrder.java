package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;

public class compoundOrder extends Order {
    @JsonProperty("orders")
    @JsonDeserialize(contentAs = simpleOrder.class)
    public ArrayList<simpleOrder> Orders ;
//<<<<<<< HEAD
//=======
//    public double Cost ;
//    public double shippingFees;
//>>>>>>> Notification

    public compoundOrder(String id){
        this.ID = "C"+id ;
        Orders = new ArrayList<>();
    }
    public compoundOrder(){
        Orders = new ArrayList<>();
    }
    public void setID(String id){
        this.ID = "C"+id ;
    }
    @Override
    public double calcCost() {
        double sum = 0 ;
        for (int i = 0; i < Orders.size(); i++) {
            sum += Orders.get(i).calcCost();
        }
        return Cost =  sum ;
    }

    @Override
    public boolean addOrder(Order o) {
        if(o != null) {
           return Orders.add((simpleOrder) o);
        }
        return false;
    }

    @Override
    public boolean removeOrder(String oID) {
        Order o = getOrder(oID) ;
        if(o!= null){
            return Orders.remove(o);
        }
        return false;
    }

    @Override
    @JsonIgnore
    public ArrayList<Product> getProducts() {
        return null;
    }

    private Order getOrder(String oID){
        for (int i = 0; i < Orders.size(); i++) {
            if(oID.equals((  (simpleOrder)Orders.get(i)).ID )){
                return Orders.get(i);
            }
        }
        return  null;
    }
}
