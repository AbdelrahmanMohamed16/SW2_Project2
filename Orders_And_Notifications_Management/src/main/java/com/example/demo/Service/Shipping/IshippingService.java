package com.example.demo.service.Shipping;

import com.example.demo.Repo.inMemory;
import com.example.demo.model.Order;
import com.example.demo.model.Response;
import com.example.demo.model.compoundOrder;
import com.example.demo.model.simpleOrder;

import java.time.Instant;
import java.util.ArrayList;

public interface IshippingService {
    public Response shipOrder(String ID);
    public ArrayList<Order> getAllShippingOrders();
    public Response cancelShippingOrder(String OID);
}
