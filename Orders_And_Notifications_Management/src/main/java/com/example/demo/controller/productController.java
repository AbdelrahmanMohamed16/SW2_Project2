package com.example.demo.controller;

import com.example.demo.model.Response;
import com.example.demo.service.Products.productService;
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
    public Response addProduct(@RequestBody Product p) {

        return service.addProduct(p);
    }
    @GetMapping(value = "/RemainingCategory/{Name}")
    public Response getRemainingCategory(@PathVariable String Name)
    {
        return service.getRemainingCategory(Name);
    }
    @GetMapping(value = "/products")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

}