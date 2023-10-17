package com.tutorial.userservice.feignclients;

import com.tutorial.userservice.models.Bike;
import com.tutorial.userservice.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service", url = "http://localhost:8002")
public interface CarFeignClient {

    @PostMapping("/car/create")
    Car save(@RequestBody Car car);

    @GetMapping("/car/by_user/{userId}")
    List<Car> getCars(@PathVariable(value = "userId") int userId);

}
