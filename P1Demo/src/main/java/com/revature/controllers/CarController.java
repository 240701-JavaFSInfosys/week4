package com.revature.controllers;

import com.revature.DAOs.UserDAO;
import com.revature.models.Car;
import com.revature.models.DTOs.IncomingCarDTO;
import com.revature.models.User;
import com.revature.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //makes the class bean, turns all HTTP Response data into JSON
@RequestMapping("/cars") //all HTTP Requests ending in /cars will come to this Controller
public class CarController {

    //autowire a CarService and a UserDAO (we need methods from both)
    private CarService cs;
    private UserDAO uDAO; //would be better to go through the UserService for this

    @Autowired
    public CarController(CarService cs, UserDAO uDAO) {
        this.cs = cs;
        this.uDAO = uDAO;
    }

    //This method takes in Car data and sends it to the CarService
    //NOTE: ResponseEntity<Object> so we can send any type of object back in the response
    @PostMapping
    public ResponseEntity<Object> addCar(@RequestBody IncomingCarDTO newCar){

        //TODO: most of this extra processing should probably be in the Service

        //We do need to build a Car object to send to the service... so we'll do that conversion
        //0 for id since it gets generated, null for User and we'll add it next
        //TODO: Could have a Car constructor with just make, model, and isFourWheelDrive
        Car car = new Car(0, newCar.getMake(), newCar.getModel(), newCar.isFourWheelDrive(), null);

        //use the DTO's userId field to get a User by its ID.
        Optional<User> u = uDAO.findById(newCar.getUserId());

        if(u.isPresent()){ //If the requested data is present (not null)...
            car.setUser(u.get()); //assign the User to the Car!
            Car returnedCar = cs.addCar(car); //send the Car to the Service
            return ResponseEntity.status(201).body(returnedCar); //send the car back in the response
        } else {
            //if the User is not present, send back an error message (400)
            return ResponseEntity.status(400).body("Couldn't find User with ID: " + newCar.getUserId());
        }

        /*Optional?? What's going on here?

         findById returns an Optional<Datatype>. What's the point?
         Optionals are often used to prevent NullPointerExceptions. The data may or may not exist
         In other words, the presence of our data is OPTIONAL.
         We can check if the value is present with .isPresent() method, and extract it with .get() */

    }

    //This method will return all cars in the HTTP Response
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(){
        //let's make this a one liner just because we can
        return ResponseEntity.ok(cs.getAllCars());
    }

    //This method will delete a Car by its ID
    @DeleteMapping("/{carId}")
    public ResponseEntity<Object> deleteCarById(@PathVariable int carId){

        //TODO: probably wrapped in a try/catch assuming the service throws exceptions

        //delete the car thru the service
        cs.deleteCarById(carId);

        //return 200 and confirmation message
        return ResponseEntity.ok("Car with ID: " + carId + " was deleted");

    }


}
