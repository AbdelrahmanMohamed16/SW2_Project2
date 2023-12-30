package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class simpleOrder implements Order  {
    public String ID ;
    @JsonProperty("userId")
    public String Customer ;
    @JsonProperty("Product")
    public ArrayList<Product> Products ;

    public double Cost ;

    // TODO: implement this feature
    public double shippingFees;

    public simpleOrder(String id , String c ,ArrayList<Product> p){
        this.ID = "S"+id ;
        this.Customer = c ;
        this.Products = p ;
    }
    public simpleOrder(){
        this.Products = new ArrayList<>();
    }

    public void setID(String ID) {
        this.ID = "S"+ID;
    }

    @Override
    public double calcCost() {
        double sum = 0 ;
        for (int i = 0; i < Products.size(); i++) {
            sum += Products.get(i).getPrice();
        }
        return Cost =  sum ;
    }
    public boolean addProduct(Product o) {
        if(o != null) {
            Products.add(o);
            return true;
        }
        return false;
    }
    public boolean removeProduct(Product o) {
        if(o != null) {
            Products.remove(o);
            return true;
        }
        return false;
    }

    @Override
    public boolean addOrder(Order o) {
        return false;
    }

    @Override
    public boolean removeOrder(String o) {
        return false;
    }
}
