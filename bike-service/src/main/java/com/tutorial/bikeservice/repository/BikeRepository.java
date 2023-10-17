package com.tutorial.bikeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<com.tutorial.bikeservice.entity.Bike, Integer> {

    List<com.tutorial.bikeservice.entity.Bike> findByUserId(int userId);

}
