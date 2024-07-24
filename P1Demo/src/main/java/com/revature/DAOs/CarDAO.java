package com.revature.DAOs;

import com.revature.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Check UserDAO for general notes on Spring Data DAOs
@Repository
public interface CarDAO extends JpaRepository<Car, Integer> {

    //TODO: probably some custom methods

}
