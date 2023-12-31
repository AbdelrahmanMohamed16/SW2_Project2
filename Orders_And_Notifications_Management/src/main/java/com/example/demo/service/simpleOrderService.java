package com.example.demo.service;
import com.example.demo.Repo.inMemory;
import com.example.demo.model.*;
import com.example.demo.Notification_Handler.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import java.time.Instant;

@Service
public class simpleOrderService {
    @Value("${shipping.maxDuration}")
    public long shippingMaxDuration;
    @Autowired
    public compoundOrderService compServ;
    @Autowired
    public UserService userServ;

    public Response placeOrder(simpleOrder order){
        Response response = new Response();
        if(order!=null){
            // check if User exist
            User u = getOrderUser(order);

            if( u == null){
                response.setStatus(false);
                response.setMessage("User Not Found");
                return response;
            }
            // check if already Logged in User
            if(!u.isLoggedUser){
                response.setStatus(false);
                response.setMessage("User Not Logged In");
                return response;
            }
            // check if all products exist and retrieve them
            if(BuildOrder(order) == null){
                response.setStatus(false);
                response.setMessage("Products Not Found");
                return response;
            }
            // SET ID
            order.setID(Integer.toString(inMemory.Orders.size()+1));
            // calc cost of Order till Now
            double orderCost  = order.calcCost();
            // deduct cost by check if user has a sufficient balance
            if(!deductCost(order,orderCost)){
                response.setStatus(false);
                response.setMessage("low Balance");
                return response;
            }
            // balance deducted well ... Continue

            // TODO: BUILD Notifications
            userServ.SendPlacingNotifications(order);
            inMemory.MostUsedTemplate.compute("PLACED",(key, value) -> value + 1);

            // add to Repo
            inMemory.Orders.put(order.ID, order);
            // return Order again
            response.setStatus(true);
            response.setMessage("Order placed Successfully");
            response.setData(order);
            return response;
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



        if(p.quantity != 0 && deductCost((simpleOrder)o,p.getPrice())){
            ((simpleOrder)o).addProduct(p);
            p.quantity--;
            p.getCat().setRemainingParts(p.getCat().getRemainingParts()-1);
            System.out.println("find product");
            o.calcCost();
            return true;
        }
        return false;
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
            refundCost((simpleOrder)o,p.getPrice());
            p.quantity++;
            p.getCat().setRemainingParts(p.getCat().getRemainingParts()+1);
            o.calcCost();
            return true;
        }
        else{
            return false;
        }
    }
    public boolean deductCost(simpleOrder order, double Money) {
        User u = getOrderUser(order);
        // check if sufficient
        if(u.Balance >= Money){
            u.Balance -= Money;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean refundCost(simpleOrder order, double Money) {
        User u = getOrderUser(order);
        u.Balance += Money;
        return true;
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
                    if(p.quantity != 0){
                        p.quantity--;
                        p.getCat().setRemainingParts(p.getCat().getRemainingParts()-1);
                        temp.add(p);
                        System.out.println("find product");
                    }
                    else {
                        System.out.println("Product has no stock");
                    }

                }
                else{
                    // not found product
                    continue;
                }
            }
            order.Products = temp ;

        }
        return order;
    }
    public Order getOrder(String OID){
        Order o =  inMemory.Orders.get(OID) ;
        if(!( o instanceof simpleOrder)){
            return null ;
        }
        return o;
    }
    public User getOrderUser(simpleOrder order){
        // check if User Exist
        return inMemory.persons.get(order.Customer);
    }
    public boolean cancelPlacedOrder(String OID){
        // place simple
        // place compound
        Order order = null;
        if (inMemory.Orders.containsKey(OID)) {
            order = inMemory.Orders.get(OID);
            if (order != null) {
                if(order instanceof simpleOrder){
                    refundCost((simpleOrder) order , order.Cost);
                }
                else{
                    compServ.refundCost((compoundOrder) order , false);
                }
                inMemory.Orders.remove(OID);
                return true;
            }
        }
        return false;
    }
}
