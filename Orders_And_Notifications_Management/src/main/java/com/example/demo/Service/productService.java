package com.example.demo.Service;

import com.example.demo.Repo.DataBase;
import com.example.demo.Repo.inMemory;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class productService {
    public String addProduct(Product p)
    {

        Category cat = p.getCat();
        if(inMemory.Products.containsKey(p.getSerialNumber()))
        {
            return "this serialNumber of this product Found before ";
        }
        else if(inMemory.Categories.containsKey(cat.getName()))
        {
            p.setCat(inMemory.Categories.get(cat.getName()));
            int remainingParts = inMemory.Categories.get(cat.getName()).getRemainingParts()+1;
            inMemory.Categories.get(cat.getName()).setRemainingParts(remainingParts);
            inMemory.Products.put(p.getSerialNumber(),p);
            return "Product Added Successfully!.";
        }
        return "this Category of this Product is not found ";
    }
    public int getRemainingCategory(String Name)
    {
        if(inMemory.Categories.get(Name)==null)
            return -1;
        else
            return inMemory.Categories.get(Name).getRemainingParts();


    }
    public static List<Product> getAllProducts() {
        List<Product>products = new ArrayList<>();
        for (String key : inMemory.Products.keySet()) {
            products.add(inMemory.Products.get(key));
        }
        return products;
    }

}