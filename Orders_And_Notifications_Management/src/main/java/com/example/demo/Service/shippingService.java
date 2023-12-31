package com.example.demo.service;

import com.example.demo.Repo.inMemory;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Instant;
import java.util.ArrayList;

@Service
public class shippingService {
    @Value("${shipping.Fee.Rate}")
    double startFee ;
    @Value("${shipping.maxDuration}")
    public long shippingMaxDuration;
    @Autowired
    compoundOrderService compServ;
    @Autowired
    simpleOrderService simpleServ;
    @Autowired
    UserService userServ;
    public String shipOrder( String ID)
    {
          Order O = inMemory.Orders.get(ID);
          if(O!=null)
          {
               //calculate shipping
               O.calcFee(startFee);
               // deduct fee if sufficient
               if (simpleOrder.class.isAssignableFrom(O.getClass()) ){
                  simpleOrder order = (simpleOrder) O;
                  if(!simpleServ.deductCost(order,O.shippingFees)){
                      return "not sufficient balance";
                  }
                  // increase EMAIL_or_SMS counter
                  userServ.SendShippingNotifications(order);
              }
               else{
                  compoundOrder order = (compoundOrder) O;
                  if(!compServ.deductCost(order, true)){
                      return "not sufficient balance";
                  }
                  // increase EMAIL_or_SMS counter
                  userServ.SendShippingNotifications(order);

              }
               // SET shipping DAte
                O.shipmentDate = Instant.now().toEpochMilli();
               // increase SHIPPING counter
               inMemory.MostUsedTemplate.compute("SHIPPING",(key, value) -> value + 1);



               // TODO: GENERATE notifications
                // UPDATe shipping order Id
              O.ID = "SHIP_"+(inMemory.shippingOrders.size()+1)+O.ID ;
                // SAVE IN DATABASE
              inMemory.shippingOrders.put(O.ID,O);
              inMemory.Orders.remove(ID);


               return "Order Added successfully ";


          }
          return "This order is not found! ";
    }
    public ArrayList<Order> getAllShippingOrders(){
        return new ArrayList<>(inMemory.shippingOrders.values()) ;
    }
    public boolean cancelShippingOrder(String OID) {
        // shipping simple
        // shipping compound

        Order order = null;
        if (inMemory.shippingOrders.containsKey(OID)) {
            order = inMemory.shippingOrders.get(OID);
            if (order != null) {
                if ((Instant.now().toEpochMilli()-order.shipmentDate) <= shippingMaxDuration) {
                    if (order instanceof simpleOrder) {
                        simpleServ.refundCost((simpleOrder) order, order.Cost + order.shippingFees);
                    } else {
                        compServ.refundCost((compoundOrder) order, true);
                    }
                    inMemory.shippingOrders.remove(OID);
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }

}
