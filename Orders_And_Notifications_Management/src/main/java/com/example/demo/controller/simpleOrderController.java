package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.Order.simpleOrderService;
import com.example.demo.model.*;

// TODO:REMOVE THIS BAD BEAHVEIOUR

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class simpleOrderController {
    @Autowired
    simpleOrderService orderService;
    //TODO: DONE..!
    @PostMapping("/simpleOrder")
    public Response placeOrder(@RequestBody simpleOrder o){

        return orderService.placeOrder(o);
    }
    //TODO: DONE..!
    @PostMapping("/simpleOrder/{orderID}/{productID}")
    public Order addProduct(@PathVariable("orderID") String OID,@PathVariable("productID") String PID){
//        // TODO: REMOVE THIS
//        inMemory.Products.put(
//                "10",
//                new Product("cola", "10", "VendorA", inMemory.Categories.get("Fruits"), 300)
//        );
        boolean res = orderService.addProduct(OID,PID) ;
        if(res){
            return orderService.getOrder(OID);
        }
        return null;
    }
    //TODO: DONE..!
    @DeleteMapping("/simpleOrder/{orderID}/{productID}")
    public Order removeProduct(@PathVariable("orderID") String OID,@PathVariable("productID") String PID){
        // TODO: REMOVE THIS
        // inMemory.Products.put("10",new Product("10","cola"));
        boolean res = orderService.removeProduct(OID,PID) ;
        if(res){
            return orderService.getOrder(OID);
        }
        return null;
    }
    @GetMapping("/orders")
    public ArrayList<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("/placedOrder/{orderId}")
    public Response cancelPlaced(@PathVariable String orderId){
        Boolean is  =  orderService.cancelPlacedOrder(orderId);
        Response res = new Response();
        if(is){
            res.setStatus(true);
            res.setMessage("Order Canceld Successfully");
            return res;
        }
        else{
            res.setStatus(false);
            res.setMessage("Order Not found");
            return res;
        }
    }

}
