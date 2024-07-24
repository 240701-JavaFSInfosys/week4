package com.revature.controllers;

//Remember, the Controller is where all of our SpringMVC logic sits to handle HTTP requests

import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Makes this class a bean and turns HTTP Response data into JSON (@Controller, @ResponseBody)
@RequestMapping("/users") //All HTTP Requests ending in /users will go to this controller
public class UserController {

    private UserService us;

    //UserController depends on UserService (it needs its methods). So let's constructor inject the Service
    @Autowired
    public UserController(UserService us) {
        this.us = us;
    }

    //A method that inserts a new User into the DB
    @PostMapping //HTTP POST Requests ending in /users will hit this method
    public ResponseEntity<User> registerUser(@RequestBody User newUser){

        //TODO: try/catch based on service method exception throws

        User u = us.registerUser(newUser);

        return ResponseEntity.status(201).body(u); //send back 201 (Created) and the new User's data

    }

    //A method that returns all Users in the DB
    @GetMapping //HTTP GET Requests ending in /users will hit this method
    public ResponseEntity<List<User>> getAllUsers(){

        //not much error handling needed, no variables, nothing crazy that can go wrong
        List<User> users = us.getAllUsers();

        return ResponseEntity.ok(users); //send back 200 (OK) and the Users

    }

}
