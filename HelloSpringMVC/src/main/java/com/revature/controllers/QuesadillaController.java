package com.revature.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController is a combination of @Controller and @ResponseBody
@RestController //This annotation makes a Class a Bean, and sets every HTTP Response to be JSON.
@RequestMapping(value="/quesadilla") //Now every request coming to /quesadilla will come to this Controller
public class QuesadillaController {

    //No more Javalin! No endpoint handlers, Spring MVC is very annotation/method driven

    //Let's make a method that returns a Quesadilla (just a mock String return)
    @GetMapping //This annotation will take in any GET requests ending in /quesadilla
    public ResponseEntity<String> getQuesadilla(){

        //We're returning a ResponseEntity, and setting the status code to 200, and a body of "Hey... :)"
        return ResponseEntity.status(200).body("Hey here is your Quesadilla. Enjoy :)");
    }

    //Now here's a method that gets a certain amount of quesadillas (based on a path variable)
    @GetMapping("/{amount}") //Takes in GET requests to /quesadilla/{some number}
    //@PathVariable lets you extract variables in the URL's path
    public ResponseEntity<String> getSomeQuesadillas(@PathVariable int amount){

        //TODO: could check for data type mismatch, catch "MethodArgumentTypeMismatchException"

        //Let's practice some error handling on the user inputted "amount" variable

        if(amount <= 0){
            return ResponseEntity.status(400).body("Select more than zero quesadillas!");
        }

        //If the user gave a valid number, then proceed with the success response
        return ResponseEntity.ok("Here are your " + amount + " quesadillas~ Enjoy!");

        //^using shorthand to return a response body with a 200 level status code (OK)

    }

    //This method will be used to send Quesadillas back (for some reason)
    @PostMapping //takes in POST requests to /quesadilla
    public ResponseEntity<String> sendQuesadillas(@RequestBody int amount){

        //@RequestBody extracts the body of the HTTP Request, and maps it to a variable

        return ResponseEntity.accepted().body("Oh... ok I guess we can take these " + amount + " quesadillas back...");
        //.accepted() is shorthand for .status(202)

    }

    //This method will let us send Quesadillas to someone else
    @PostMapping("/send")
    public ResponseEntity<String> sendQuesadillasToSomeone(@RequestBody int amount){
        return ResponseEntity.accepted().body("Thanks for the " + amount + " quesadillas!");
    }

    /*Before adding /send to the URL endpoint mapping, we got an "Ambiguous Mapping" error
    SpringMVC won't let us have two identical mappings, since it won't know where to send requests
    The solution is to make your endpoints mappings more RESTful and give them unique paths*/

}
