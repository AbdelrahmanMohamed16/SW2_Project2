package com.example.demo.Service;
import com.example.demo.Repo.inMemory;
import com.example.demo.model.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class simpleOrderService {
    public Order placeOrder(simpleOrder order){
        if(order!=null){
            // check if User exist
            User u = checkOrderCustomer(order);
            if( u == null){
                return null;
            }
            // check if already Logged in User
            if(!u.isLoggedUser){
                return null;
            }
            // check if all products exist and retrieve them
            if(BuildOrder(order) == null) return null;
            // SET ID
            order.setID(Integer.toString(inMemory.Orders.size()+1));
            // calc cost of Order till Now
            order.calcCost();
            // add to Repo
            inMemory.Orders.put(order.ID, order);
            // return Order again
            Channel ch = new Email();
            if(ch instanceof Email)
            {
                int x= inMemory.mostUsedEmail.get(order.Customer);
                inMemory.mostUsedEmail.put(order.Customer,++x);
                inMemory.mostUsedPhoneAndEmail.put(order.Customer,++x);
            }
            else if(ch instanceof SMS)
            {
                String s = inMemory.persons.get(order.Customer).mobileNumber;
                int x= inMemory.mostUsedPhone.get(s);
                inMemory.mostUsedEmail.put(s,++x);
                inMemory.mostUsedPhoneAndEmail.put(s,++x);
            }
            return order;
        }
        return null;
    }
    public boolean addProduct(String oID, String pID){
        Order o =  inMemory.Orders.get(oID) ;
        if(!( o instanceof simpleOrder)){
            return false ;
        }
        Product p = inMemory.Products.get(pID);
        if(p == null) return false;

        if(((simpleOrder)o).addProduct(p)){
            // added well
            o.calcCost();
            return true;
        }
        else{
            return false;
        }

    }
    public boolean removeProduct(String oID, String pID){
        Order o =  inMemory.Orders.get(oID) ;
        if(!( o instanceof simpleOrder)){
            return false ;
        }
        Product p = inMemory.Products.get(pID);
        if(p == null) return false;

        if(((simpleOrder)o).removeProduct(p)){
            // added well
            o.calcCost();
            return true;
        }
        else{
            return false;
        }
    }
    public Order getOrder(String OID){
        Order o =  inMemory.Orders.get(OID) ;
        if(!( o instanceof simpleOrder)){
            return null ;
        }
        return o;
    }
    public ArrayList<Order> getAllOrders(){
        return new ArrayList<>(inMemory.Orders.values()) ;
    }
    public simpleOrder BuildOrder(simpleOrder order){
        if (order == null) return null;
        if(order.Products != null){
            int n_product = order.Products.size();
            ArrayList<Product> temp = new ArrayList<>();
            for (int i = 0; i < n_product ; i++) {
                if( inMemory.Products.containsKey(order.Products.get(i).getSerialNumber())){
                    Product p = inMemory.Products.get(order.Products.get(i).getSerialNumber());
                    temp.add(p);
                }
                else{
                    // not found product

                }
            }
            order.Products = temp ;

        }
        return order;
    }
    public User checkOrderCustomer(simpleOrder order){
        return inMemory.persons.get(order.Customer);
    }
}
