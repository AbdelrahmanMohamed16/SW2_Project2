package com.example.demo.controller;

import com.example.demo.service.productService;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class productController {
    @Autowired
    productService service ;

    public productController(productService service) {
        this.service = service;
    }
    @PostMapping(value = "/products")
    public String addProduct(@RequestBody Product p)
    {

        return service.addProduct(p);
    }
    @GetMapping(value = "/RemainingCategory/{Name}")
    public int getRemainingCategory(@PathVariable String Name)
    {
        return service.getRemainingCategory(Name);
    }
    @GetMapping(value = "/products")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }


}