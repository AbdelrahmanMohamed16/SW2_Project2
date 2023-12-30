package com.example.demo.model;

import java.util.ArrayList;

public class compoundOrder implements Order {
    public String ID ;
    public ArrayList<Order> Orders ;
    public double Cost ;
    public double shippingFees;

    public compoundOrder(String id){
        this.ID = "C"+id ;
        Orders = new ArrayList<>();
    }
    public compoundOrder(){

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
        return sum ;
    }

    @Override
    public boolean addOrder(Order o) {
        if(o != null) {
           return Orders.add(o);
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
    private Order getOrder(String oID){
        for (int i = 0; i < Orders.size(); i++) {
            if(oID.equals((  (simpleOrder)Orders.get(i)).ID )){
                return Orders.get(i);
            }
        }
        return  null;
    }


}
