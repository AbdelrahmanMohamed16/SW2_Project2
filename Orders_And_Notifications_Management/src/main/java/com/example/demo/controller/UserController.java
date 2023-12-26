package com.example.demo.controller;

import com.example.demo.Repo.inMemory;
import com.example.demo.model.Response;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService = new UserService();

    @PostMapping("/signUp")
    public Response signUp(@RequestBody User user) {
        boolean done = userService.addUser(user);
        Response response = new Response();
        System.out.println(inMemory.persons.get("user123"));
        if (!done) {
            response.setStatus(false);
            response.setMessage("this account already exist");
            return response;
        } else {
            response.setStatus(true);
            response.setMessage("created account successfully");
            return response;
        }
    }

    @GetMapping("/login/{id}")
    public Response login(@PathVariable("id") String id, @RequestBody String password) {
        User acc = new User();
        acc = userService.checkUserExist(id);
        Response response = new Response();
        if (acc != null) {
            if (acc.checkPassword(id, password)) {
                response.setStatus(true);
                response.setMessage("login successfully , hello " + acc.Name);
                return response;
            } else {
                response.setStatus(false);
                response.setMessage("invalid password , please try again");
                return response;
            }
        } else {
            response.setStatus(false);
            response.setMessage("you dont have account with id: " + id);
            return response;
        }

    }

    @GetMapping("/Users")
    public Map<String, User> allUsers() {
        return userService.getAllUsers();
    }
}
