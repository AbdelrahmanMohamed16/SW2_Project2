package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.simpleOrderService;
import com.example.demo.model.*;

// TODO:REMOVE THIS BAD BEAHVEIOUR
import com.example.demo.Repo.inMemory;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class simpleOrderController {
    @Autowired
    simpleOrderService orderService;
    //TODO: DONE..!
    @PostMapping("/simpleOrder")
    public Order placeOrder(@RequestBody simpleOrder o){
//        // TODO: REMOVE THIS
//        inMemory.Products.put(
//                "10",
//                new Product("cola", "10", "VendorA", inMemory.Categories.get("Fruits"), 300)
//        );
//
//        inMemory.Products.put(
//                "11",
//                new Product("pepsi", "11", "VendorA", inMemory.Categories.get("Fruits"), 300)
//        );
//        inMemory.Products.put(
//                "12",
//                new Product("cocoa", "12", "VendorA", inMemory.Categories.get("Fruits"), 300)
//        );

        Order res = orderService.placeOrder(o) ;
        if(res != null){
            return res ;
        }
        return null;
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
    //TODO: DONE..!
    @GetMapping("/orders")
    public ArrayList<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("/placedOrder/{orderId}")
    public boolean cancelPlaced(@PathVariable String orderId){
        return orderService.cancelPlacedOrder(orderId);
    }
    @DeleteMapping("/shippingOrder/{orderId}")
    public boolean cancelShipping(@PathVariable String orderId){
        return orderService.cancelShippingOrder(orderId);
    }

}
