package com.example.demo.controller;

import com.example.demo.Service.shippingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class shippingController {
    shippingService service;
    @PostMapping("/shipOrder/{OrderID}")
    public String shipOrder(@PathVariable("OrderID") String ID)
    {
            return service.shipOrder(ID);
    }

}
