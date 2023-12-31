package com.example.demo.controller;

import com.example.demo.service.shippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
