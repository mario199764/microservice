package com.tutorial.carservice.controller;

import com.tutorial.carservice.entity.Car;
import com.tutorial.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;


    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        List<Car> cars = carService.getCars();
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getBydId(@PathVariable("id") int id){
        Car car = carService.getCarById(id);
        if(car == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @GetMapping("/by_user/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId){
        List<Car> cars = carService.findByUserId(userId);
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/create")
    public ResponseEntity<Car> save(@RequestBody Car car){
        Car carNew = carService.save(car);
        return ResponseEntity.ok(carNew);
    }
}
