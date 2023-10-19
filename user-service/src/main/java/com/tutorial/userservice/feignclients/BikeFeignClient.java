package com.tutorial.userservice.feignclients;

import com.tutorial.userservice.models.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "bike-service")
public interface BikeFeignClient {

    @PostMapping("/bike/create")
    Bike save(@RequestBody Bike bike);

    @GetMapping("/bike/by_user/{userId}")
    List<Bike> getBikes(@PathVariable(value = "userId") int userId);

}
