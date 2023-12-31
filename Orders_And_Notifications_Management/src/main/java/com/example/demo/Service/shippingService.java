package com.example.demo.service;

import com.example.demo.Repo.inMemory;
import com.example.demo.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class shippingService {
    public String shipOrder( String ID)
    {
          Order O = inMemory.Orders.get(ID);
          if(O!=null)
          {
               //calculate shipping
               inMemory.shippingOrders.put(ID,inMemory.Orders.get(ID));
               inMemory.Orders.remove(ID);
               if(O instanceof simpleOrder)
               {
                   Channel ch = new Email();
                   if(ch instanceof Email)
                   {
                       if (inMemory.mostUsedEmail.containsKey(((simpleOrder) O).Customer)) {
                           int x = inMemory.mostUsedEmail.get(((simpleOrder) O).Customer);
                           inMemory.mostUsedEmail.put(((simpleOrder) O).Customer, ++x);
                           inMemory.mostUsedPhoneAndEmail.put(((simpleOrder) O).Customer, ++x);
                       }
                       else {
                           inMemory.mostUsedEmail.put(((simpleOrder) O).Customer, 1);
                           inMemory.mostUsedPhoneAndEmail.put(((simpleOrder) O).Customer, 1);
                       }
                   }
                   else if(ch instanceof SMS)
                   {
                       String s = inMemory.persons.get(((simpleOrder) O).Customer).mobileNumber;
                       if (inMemory.mostUsedPhone.containsKey(s)) {
                           int x = inMemory.mostUsedPhone.get(s);
                           inMemory.mostUsedPhone.put(s, ++x);
                           inMemory.mostUsedPhoneAndEmail.put(s, ++x);
                       }
                       else {
                           inMemory.mostUsedPhone.put(s, 1);
                           inMemory.mostUsedPhoneAndEmail.put(s, 1);
                       }
                   }
               }
               else if(O instanceof compoundOrder)
               {
                   for(int i = 0; i < ((compoundOrder) O).Orders.size(); i++)
                   {
                       Channel ch = new Email();
                       if(ch instanceof Email)
                       {
                           if (inMemory.mostUsedEmail.containsKey(((compoundOrder) O).Orders.get(i).Customer)) {
                               int x = inMemory.mostUsedEmail.get(((compoundOrder) O).Orders.get(i).Customer);
                               inMemory.mostUsedEmail.put(((compoundOrder) O).Orders.get(i).Customer, ++x);
                               inMemory.mostUsedPhoneAndEmail.put(((compoundOrder) O).Orders.get(i).Customer, ++x);
                           }
                           else {
                               inMemory.mostUsedEmail.put(((compoundOrder) O).Orders.get(i).Customer, 1);
                               inMemory.mostUsedPhoneAndEmail.put(((compoundOrder) O).Orders.get(i).Customer, 1);
                           }
                       }
                       else if(ch instanceof SMS)
                       {
                           String s = inMemory.persons.get(((compoundOrder) O).Orders.get(i).Customer).mobileNumber;
                           if (inMemory.mostUsedPhone.containsKey(s)) {
                               int x = inMemory.mostUsedPhone.get(s);
                               inMemory.mostUsedPhone.put(s, ++x);
                               inMemory.mostUsedPhoneAndEmail.put(s, ++x);
                           }
                           else {
                               inMemory.mostUsedPhone.put(s, 1);
                               inMemory.mostUsedPhoneAndEmail.put(s, 1);
                           }
                       }
                   }
               }
               return "Order Added successfully ";


          }
          return "This order is not found! ";
    }

}
