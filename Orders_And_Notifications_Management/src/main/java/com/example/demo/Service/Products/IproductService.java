package com.example.demo.service.Products;

import com.example.demo.Repo.inMemory;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.Response;

import java.util.ArrayList;
import java.util.List;

public interface IproductService {
    public Response addProduct(Product p);
    public Response getRemainingCategory(String Name);
    public List<Product> getAllProducts();
}
