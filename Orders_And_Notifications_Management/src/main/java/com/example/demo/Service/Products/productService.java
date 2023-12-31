package com.example.demo.service.Products;

import com.example.demo.Repo.inMemory;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.Response;
import com.example.demo.service.Products.IproductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class productService implements IproductService {
    public Response addProduct(Product p)
    {
        Response response = new Response();
        Category cat = p.getCat();
        if(inMemory.Products.containsKey(p.getSerialNumber()))
        {
            response.setStatus(false);
            response.setMessage("this serialNumber of this product Found before ");
            return response;
        }
        else if(inMemory.Categories.containsKey(cat.getName()))
        {
            p.setCat(inMemory.Categories.get(cat.getName()));
            int remainingParts = inMemory.Categories.get(cat.getName()).getRemainingParts()+p.quantity;
            inMemory.Categories.get(cat.getName()).setRemainingParts(remainingParts);
            inMemory.Products.put(p.getSerialNumber(),p);
            response.setStatus(true);
            response.setMessage("Product Added Successfully!.");
            return response;
        }
        response.setStatus(false);
        response.setMessage("this Category of this Product is not found ");
        return response;
    }
    public Response getRemainingCategory(String Name) {
        Response message = new Response();
        if (inMemory.Categories.get(Name) == null){
            message.setStatus(false);
            message.setData(0);
            message.setMessage(Name + " Not found Categories");
        }
        else {
            message.setStatus(true);
            message.setData(inMemory.Categories.get(Name).getRemainingParts());
            message.setMessage("Remaining parts of "+Name+" in our System");
        }
        return message;

    }
    public List<Product> getAllProducts() {
        List<Product>products = new ArrayList<>();
        for (String key : inMemory.Products.keySet()) {
            products.add(inMemory.Products.get(key));
        }
        return products;
    }

}