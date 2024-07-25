package com.revature.services;

import com.revature.DAOs.CarDAO;
import com.revature.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    //autowire a CarDAO
    private CarDAO cDAO;

    @Autowired
    public CarService(CarDAO cDAO) {
        this.cDAO = cDAO;
    }

    //This method will take Car data from the Controller and send it to the DAO
    public Car addCar(Car newCar){

        //TODO: error handling

        Car c = cDAO.save(newCar);

        return c;

    }

    //This method will return all the Cars in the DB from the DAO
    public List<Car> getAllCars(){
        return cDAO.findAll();
    }

    //This method will take in a Car ID and delete it through the DAO
    public void deleteCarById(int id){

        //First we need to get the Car by its id, for error handling AND for deletion
        //The car WILL NOT fully delete unless it's also deleted from the User's List<Car>

        //we're just gonna use .get() straight up this time (instead of Optional.isPresent())
        Car car = cDAO.findById(id).get();

        //delete the Car from the User's List<Car>
        car.getUser().getCars().remove(car);

        //now, perform the actual delete
        cDAO.deleteById(id);
    }

    //This method will take in an int and find Cars with that user ID from the DAO
    public List<Car> getCarsByUserId(int userId){

        //TODO: error handling, check for valid id, maybe check for empty return

        return cDAO.findByUserUserId(userId);

    }

}
