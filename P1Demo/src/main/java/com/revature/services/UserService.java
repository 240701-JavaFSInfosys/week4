package com.revature.services;

import com.revature.DAOs.UserDAO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //1 of the 4 stereotype annotations. Makes this Class a Bean
public class UserService {

    //define (but not initialize) a UserDAO object
    private UserDAO userDAO;

    //How can we get access to the methods of our UserDAO? DEPENDENCY INJECTION!!
    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //A method that takes in a User object from the controller, and sends it to the DAO
    public User registerUser(User newUser){

        //TODO: error handling and other business logic!

        //.save() lets us insert data into the DB. We can also use this for updates.
        //It also returns the user in question, so we can save it to a variable
        User u = userDAO.save(newUser);

        return u; //send back to the Controller

        //NOTE: You can definitely just return the method call, I just like to spell it out a bit

    }

    //This method gets all users from the UserDAO, and sends it back to the Controller
    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    //This method gets a user by their username
    public User getUserByUsername(String username){

        //TODO: error handling for user not found, username blank, etc.

        return userDAO.findByUsername(username);

    }

}
