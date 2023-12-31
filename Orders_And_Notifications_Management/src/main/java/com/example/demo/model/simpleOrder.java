package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class simpleOrder extends Order  {
    @JsonProperty("userId")
    public String Customer ;
    public ArrayList<Product> Products ;

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
    @Override
    public double calcFee(double start){
        return shippingFees = Products.size()+start ;
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

    @Override
    public ArrayList<Product> getProducts() {
        return Products;
    }

}
