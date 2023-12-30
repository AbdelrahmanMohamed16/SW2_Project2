package com.example.demo.Service;

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
               inMemory.shippingOrders.put(ID,inMemory.Orders.get(ID));
               inMemory.Orders.remove(ID);
               //calculate shipping
               if(O instanceof simpleOrder)
               {
                   Channel ch = new Email();
                   if(ch instanceof Email)
                   {
                       int x= inMemory.mostUsedEmail.get(((simpleOrder) O).Customer);
                       inMemory.mostUsedEmail.put(((simpleOrder) O).Customer,++x);
                       inMemory.mostUsedPhoneAndEmail.put(((simpleOrder) O).Customer,++x);
                   }
                   else if(ch instanceof SMS)
                   {
                       String s = inMemory.persons.get(((simpleOrder) O).Customer).mobileNumber;
                       int x= inMemory.mostUsedPhone.get(s);
                       inMemory.mostUsedEmail.put(s,++x);
                       inMemory.mostUsedPhoneAndEmail.put(s,++x);
                   }
               }
               else if(O instanceof compoundOrder)
               {
                   for(int i = 0; i < ((compoundOrder) O).Orders.size(); i++)
                   {
                       Channel ch = new Email();
                       if(ch instanceof Email)
                       {
                           int x= inMemory.mostUsedEmail.get(((compoundOrder) O).Orders.get(i).Customer);
                           inMemory.mostUsedEmail.put(((compoundOrder) O).Orders.get(i).Customer,++x);
                           inMemory.mostUsedPhoneAndEmail.put(((compoundOrder) O).Orders.get(i).Customer,++x);
                       }
                       else if(ch instanceof SMS)
                       {
                           String s = inMemory.persons.get(((compoundOrder) O).Orders.get(i).Customer).mobileNumber;
                           int x= inMemory.mostUsedPhone.get(s);
                           inMemory.mostUsedEmail.put(s,++x);
                           inMemory.mostUsedPhoneAndEmail.put(s,++x);
                       }
                   }
               }
               return "Order Added successfully ";


          }
          return "This order is not found! ";
    }

}
