package com.tutorial.carservice.service;

import com.tutorial.carservice.entity.Car;
import com.tutorial.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getCars(){
        return CarService.this.carRepository.findAll();
    }

    public Car getCarById(int id){
        return CarService.this.carRepository.findById(id).orElse(null);
    }

    public Car save(Car car){
        Car newCar = CarService.this.carRepository.save(car);
        return newCar;
    }

    public List<Car> findByUserId(int userId){
        return carRepository.findByUserId(userId);
    }
}
