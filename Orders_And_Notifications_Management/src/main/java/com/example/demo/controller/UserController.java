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
        if (!done) {
            response.setStatus(false);
            response.setMessage("this account already exist");
            user.isLoggedUser = false;
            return response;
        } else {
            response.setStatus(true);
            response.setMessage("created account successfully");
            user.isLoggedUser = false;
            return response;
        }
    }

    @GetMapping("/login/{email}")
    public Response login(@PathVariable("email") String email, @RequestBody String password) {
        User acc = new User();
        acc = userService.checkUserExist(email);
        Response response = new Response();
        if (acc != null) {
            if (acc.checkPassword(email, password)) {
                response.setStatus(true);
                response.setMessage("login successfully , hello " + acc.Name);
                acc.isLoggedUser = true;
                return response;
            } else {
                response.setStatus(false);
                response.setMessage("invalid password , please try again");
                acc.isLoggedUser = false;
                return response;
            }
        } else {
            response.setStatus(false);
            response.setMessage("you dont have account with email: " + email);
            return response;
        }

    }

    @GetMapping("/Users")
    public Map<String, User> allUsers() {
        return userService.getAllUsers();
    }
}
