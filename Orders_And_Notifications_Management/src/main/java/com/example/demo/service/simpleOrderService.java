package com.example.demo.service;
import com.example.demo.Repo.inMemory;
import com.example.demo.model.*;
import org.springframework.stereotype.Service;


@Service
public class simpleOrderService {
    public Order placeOrder(simpleOrder order){
        if(order!=null){
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
        return ((simpleOrder)o).addProduct(p);
    }
    public boolean removeProduct(String oID, String pID){
        Order o =  inMemory.Orders.get(oID) ;
        if(!( o instanceof simpleOrder)){
            return false ;
        }
        Product p = inMemory.Products.get(pID);
        return ((simpleOrder)o).removeProduct(p);
    }
    public Order getOrder(String OID){
        Order o =  inMemory.Orders.get(OID) ;
        if(!( o instanceof simpleOrder)){
            return null ;
        }
        return o;
    }
}
