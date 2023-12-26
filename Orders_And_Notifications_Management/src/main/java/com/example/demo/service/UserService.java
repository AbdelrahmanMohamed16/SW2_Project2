package com.example.demo.service;

import com.example.demo.Repo.inMemory;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    public boolean addUser(User user){
        try{
            if(inMemory.persons.get(user.ID) != null){
                return false;
            }
            else{
                inMemory.persons.put(user.ID , user);
                return true;
            }
        }catch (Exception e){
            System.out.println("Exception in addUser as" + e.getMessage());
            return false;
        }
    }
    public User checkUserExist(String id){
        try {
            return inMemory.persons.get(id);
        } catch (Exception e) {
            System.out.println("Exception in checkUserExist as" + e.getMessage());
        }
        return null;
    }


    public Map<String, User> getAllUsers(){
        return inMemory.persons;
    }
}
