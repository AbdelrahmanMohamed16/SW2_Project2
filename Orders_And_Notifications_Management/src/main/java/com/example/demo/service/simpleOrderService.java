package com.example.demo.service;
import com.example.demo.Repo.inMemory;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


@Service
public class simpleOrderService {
    public Order placeOrder(simpleOrder order){
        if(order!=null){
            // check if User exist
            User u = checkOrderCustomer(order);
            if( u == null){
                return null;
            }
            // check if already Logged in User
            if(!u.isLoggedUser){
                return null;
            }
            // check if all products exist and retrieve them
            if(BuildOrder(order) == null) return null;
            // SET ID
            order.setID(Integer.toString(inMemory.Orders.size()+1));
            // calc cost of Order till Now
            order.calcCost();
            // add to Repo
            inMemory.Orders.put(order.ID, order);
            // return Order again
            return order;
        }
        return null;
    }
    public boolean addProduct(String oID, String pID){
        Order o =  inMemory.Orders.get(oID) ;
        if(!( o instanceof simpleOrder)){
            return false ;
        }
        Product p = inMemory.Products.get(pID);
        if(p == null) return false;

        if(((simpleOrder)o).addProduct(p)){
            // added well
            o.calcCost();
            return true;
        }
        else{
            return false;
        }

    }
    public boolean removeProduct(String oID, String pID){
        Order o =  inMemory.Orders.get(oID) ;
        if(!( o instanceof simpleOrder)){
            return false ;
        }
        Product p = inMemory.Products.get(pID);
        if(p == null) return false;

        if(((simpleOrder)o).removeProduct(p)){
            // added well
            o.calcCost();
            return true;
        }
        else{
            return false;
        }
    }
    public Order getOrder(String OID){
        Order o =  inMemory.Orders.get(OID) ;
        if(!( o instanceof simpleOrder)){
            return null ;
        }
        return o;
    }
    public ArrayList<Order> getAllOrders(){
        return new ArrayList<>(inMemory.Orders.values()) ;
    }
    public simpleOrder BuildOrder(simpleOrder order){
        if (order == null) return null;
        if(order.Products != null){
            int n_product = order.Products.size();
            ArrayList<Product> temp = new ArrayList<>();
            for (int i = 0; i < n_product ; i++) {
                if( inMemory.Products.containsKey(order.Products.get(i).getSerialNumber())){
                    Product p = inMemory.Products.get(order.Products.get(i).getSerialNumber());
                    temp.add(p);
                }
                else{
                    // not found product

                }
            }
            order.Products = temp ;

        }
        return order;
    }
    public User checkOrderCustomer(simpleOrder order){
        return inMemory.persons.get(order.Customer);
    }
    public boolean cancelPlacedOrder(String OID){
        // place simple
        // place compound
        Order order = null;
        if (inMemory.Orders.containsKey(OID)) {
            order = inMemory.Orders.get(OID);
            if (order != null) {
                order.refundCost(order.getCost());
                inMemory.Orders.remove(OID);
                return true;
            }
        }
        return false;
    }

    public boolean cancelShippingOrder(@Value("${shipping.maxDuration}") Long shippingMaxDuration, String OID){
        // shipping simple
        // shipping compound
        Order order = null;
        if (inMemory.shippingOrders.containsKey(OID)) {
            order = inMemory.shippingOrders.get(OID);
            if (order != null) {
                if ((order.shipmentDate - Instant.now().toEpochMilli()) <= shippingMaxDuration) {
                    order.refundCost(order.getCost());
                    order.refundCost(order.getshippingFees());
                    inMemory.shippingOrders.remove(OID);
                    return true;
                }
            }
        }
        return false;
    }
}
