package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.simpleOrderService;
import com.example.demo.model.*;

// TODO:REMOVE THIS BAD BEAHVEIOUR
import com.example.demo.Repo.inMemory;
@RestController
@RequestMapping("/api")
public class simpleOrderController {
    @Autowired
    simpleOrderService orderService;
    @PostMapping("/simpleOrder")
    public Order placeOrder(@RequestBody simpleOrder o){
        Order res = orderService.placeOrder(o) ;
        if(res != null){
            return res ;
        }
        return null;
    }
    @PostMapping("/simpleOrder/{orderID}/{productID}")
    public Order addProduct(@PathVariable("orderID") String OID,@PathVariable("productID") String PID){
        // TODO: REMOVE THIS
        // inMemory.Products.put("10",new Product("10","cola"));
        boolean res = orderService.addProduct(OID,PID) ;
        if(res){
            return orderService.getOrder(OID);
        }
        return null;
    }
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

}
