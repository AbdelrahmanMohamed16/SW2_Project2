package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.service.shippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class shippingController {
    @Autowired
    shippingService service;
    @PostMapping("/shipOrder/{OrderID}")
    public String shipOrder(@PathVariable("OrderID") String ID)
    {
            return service.shipOrder(ID);
    }
    @GetMapping("/shippingOrders")
    public ArrayList<Order> getAllOrders(){
        return service.getAllShippingOrders();
    }
    @DeleteMapping("/shippingOrder/{orderId}")
    public boolean cancelShipping(@PathVariable String orderId){
        return service.cancelShippingOrder(orderId);
    }


}
