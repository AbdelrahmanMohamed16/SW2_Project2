package com.example.demo.service;

import com.example.demo.Notification_Handler.Notification;
import com.example.demo.Notification_Handler.PlacementNotification;
import com.example.demo.Repo.inMemory;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class compoundOrderService {
    @Autowired
    public UserService userServ;

    public Response placeOrder(compoundOrder order){
        Response response = new Response();
            if(order != null) {
                // SET ID
                order.setID(Integer.toString(inMemory.Orders.size() + 1));
                // calc cost of Order till Now
                //  if simple orders add each order added i
                if(order.Orders != null){
                    simpleOrderService serv = new simpleOrderService();
                    String ID ;
                    for (int i = 0; i < order.Orders.size(); i++) {
                        // Skip order with non-exist User
                        User u = serv.getOrderUser(order.Orders.get(i)) ;
                        if(u == null){
                            order.Orders.remove(i);
                            System.out.println("User not found..!");
                            i--;
                            continue;
                        }
                        if (i == 0 && !u.isLoggedUser) {
                            // check first User is logged in
                            response.setStatus(false);
                            response.setMessage("User Not Logged");
                            return response;
                        }
                        serv.BuildOrder(order.Orders.get(i));
                        ID = (i + 1) + order.ID;
                        order.Orders.get(i).setID(ID);
                    }

                }
                // calc orders cost
                order.calcCost();

                // TODO: deduct cost from each User order
                if(!deductCost(order,false)){
                    response.setStatus(false);
                    response.setMessage("low balance");
                    return response;
                }


                // TODO:Build Notification
                // count each order User email or Phone
                userServ.SendPlacingNotifications(order);
                inMemory.MostUsedTemplate.compute("PLACED",(key, value) -> value + 1);
                // add to Repo
                inMemory.Orders.put(order.ID, order);
                // return Order again
                response.setStatus(true);
                response.setMessage("PLaced Successfully");
                response.setData(order);
                return response;
            }

        System.out.println("default line 53");
        return null;
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
    public boolean deductCost(compoundOrder order, boolean isShipping) {
        simpleOrderService serv = new simpleOrderService();
        for (int i = 0; i < order.Orders.size(); i++) {
                if(isShipping){
                    if(!serv.deductCost(order.Orders.get(i),order.Orders.get(i).shippingFees)){
                        return false;
                    }
                }
                else{
                    if(!serv.deductCost(order.Orders.get(i),order.Orders.get(i).Cost)){
                        return false;
                    }
                }
        }
        return true;
    }
    public boolean refundCost(compoundOrder order, boolean isShipping) {
        simpleOrderService serv = new simpleOrderService();
        for (int i = 0; i < order.Orders.size(); i++) {
            if(isShipping){
                serv.refundCost(order.Orders.get(i),order.Orders.get(i).Cost+order.Orders.get(i).shippingFees);
            }
            else{
                serv.refundCost(order.Orders.get(i),order.Orders.get(i).Cost);
            }
        }
        return true;
    }
}
