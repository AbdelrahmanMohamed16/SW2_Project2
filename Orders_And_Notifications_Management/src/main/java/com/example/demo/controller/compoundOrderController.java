package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.compoundOrder;
import com.example.demo.model.simpleOrder;
import com.example.demo.service.compoundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class compoundOrderController {
    @Autowired
    compoundOrderService orderService;

    @PostMapping("/compoundOrder")
    public Order placeOrder(@RequestBody compoundOrder o) {
        Order res = orderService.placeOrder(o) ;
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
