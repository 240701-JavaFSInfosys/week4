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

}