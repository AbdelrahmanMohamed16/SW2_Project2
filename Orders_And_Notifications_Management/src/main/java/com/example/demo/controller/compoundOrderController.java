package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.Response;
import com.example.demo.model.simpleOrder;
import com.example.demo.model.compoundOrder;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class compoundOrderController {
    @Autowired
    compoundOrderService orderService;

    @PostMapping("/compoundOrder")
    public Response placeOrder(@RequestBody compoundOrder o) {
        Response res = orderService.placeOrder(o) ;
        return res ;
    }

    @PostMapping("/compoundOrder/{COrderID}")
    public Order addOrder(@PathVariable("COrderID") String COID, @RequestBody simpleOrder o){
        boolean res = orderService.addOrder(COID,o) ;
        if(res){
            return orderService.getOrder(COID);
        }
        return null;
    }
}
