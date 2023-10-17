package com.tutorial.bikeservice.service;

import com.tutorial.bikeservice.entity.Bike;
import com.tutorial.bikeservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getBikes(){
        return BikeService.this.bikeRepository.findAll();
    }

    public Bike getBikeById(int id){
        return BikeService.this.bikeRepository.findById(id).orElse(null);
    }

    public Bike save(Bike bike){
        Bike newBike = BikeService.this.bikeRepository.save(bike);
        return newBike;
    }

    public List<Bike> findByUserId(int userId){
        return bikeRepository.findByUserId(userId);
    }
}
