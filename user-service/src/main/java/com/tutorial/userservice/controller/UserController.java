package com.tutorial.userservice.controller;

import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.models.Bike;
import com.tutorial.userservice.models.Car;
import com.tutorial.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getUsers();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getBydId(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(userId);
        if(cars.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Bike> bikes = userService.getBikes(userId);
        if(bikes.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/get_all/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId){
        Map<String, Object> result = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }



    @PostMapping("/create")
    public ResponseEntity<User> save(@RequestBody User user){
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> save(@PathVariable("userId") int userId, @RequestBody Car car){
        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        Car newCar = userService.saveCar(userId, car);
        return ResponseEntity.ok(car);
    }

    @PostMapping("savebike/{userId}")
    public ResponseEntity<Bike> save(@PathVariable("userId") int userId, @RequestBody Bike bike){
        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        Bike newBike = userService.saveBike(userId, bike);
        return ResponseEntity.ok(bike);
    }
}
