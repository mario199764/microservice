package com.tutorial.userservice.service;

import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.feignclients.BikeFeignClient;
import com.tutorial.userservice.models.Bike;
import com.tutorial.userservice.models.Car;
import com.tutorial.userservice.repository.UserRepository;
import com.tutorial.userservice.feignclients.CarFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        User newUser = userRepository.save(user);
        return newUser;
    }

    public List<Car> getCars(int userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/by_user/" + userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/by_user/" + userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId, Car car){
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike saveBike(int userId, Bike bike){
        bike.setUserId(userId);
        Bike newBike = bikeFeignClient.save(bike);
        return newBike;
    }

    public Map<String, Object> getUserAndVehicles(int userId){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            result.put("Mensaje", "no existe este usuario");
            return result;
        }
        result.put("User", user);
        List<Car> cars = carFeignClient.getCars(userId);
        if(cars.isEmpty()){
            result.put("Cars", "este usuario no tiene coches");
        }else{
            result.put("Cars", cars);
        }
        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if(bikes.isEmpty()){
            result.put("Bikes", "este usuario no tiene motos");
        }else{
            result.put("Bikes", bikes);
        }
        return result;
    }

}
