package com.example.demo.Service;

import com.example.demo.Repo.inMemory;
import com.example.demo.model.Order;
import com.example.demo.model.*;
import org.springframework.stereotype.Service;

@Service
public class compoundOrderService {

    public Order placeOrder(){
        compoundOrder order = new compoundOrder();
            // SET ID
            order.setID(Integer.toString(inMemory.Orders.size()+1));
            // calc cost of Order till Now
            order.calcCost();
            // add to Repo
            inMemory.Orders.put(order.ID, order);
            // return Order again
            return order;
    }
    public boolean addOrder(String cOID, Order o){
        Order x =  inMemory.Orders.get(cOID) ;
        if(!( x instanceof compoundOrder)){
            return false ;
        }
        compoundOrder c = (compoundOrder) x;
        String ID = (c.Orders.size() + 1) +c.ID;
        ((simpleOrder)o).setID(ID);
        return c.addOrder(o);
    }
    public boolean removeOrder(String cOID, String oID){
        Order c =  inMemory.Orders.get(cOID) ;
        if(!( c instanceof compoundOrder)){
            return false ;
        }
        return ((compoundOrder)c).removeOrder(oID);
    }

    public Order getOrder(String COID){
        Order o =  inMemory.Orders.get(COID) ;
        if(!( o instanceof compoundOrder)){
            return null ;
        }
        return o;
    }
}
