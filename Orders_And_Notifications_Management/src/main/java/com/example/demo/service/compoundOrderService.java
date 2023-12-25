package com.example.demo.service;

import com.example.demo.Repo.inMemory;
import com.example.demo.model.Order;
import com.example.demo.model.*;
import org.springframework.stereotype.Service;

@Service
public class compoundOrderService {
    public boolean addOrder(String cOID, Order o){
        Order c =  inMemory.Orders.get(cOID) ;
        if(!( c instanceof compoundOrder)){
            return false ;
        }
        return ((compoundOrder)c).addOrder(o);
    }
    public boolean removeOrder(String cOID, String oID){
        Order c =  inMemory.Orders.get(cOID) ;
        if(!( c instanceof compoundOrder)){
            return false ;
        }
        return ((compoundOrder)c).removeOrder(oID);
    }
}
