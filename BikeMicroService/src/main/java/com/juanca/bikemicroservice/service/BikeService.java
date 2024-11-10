package com.juanca.bikemicroservice.service;

import com.juanca.bikemicroservice.entity.Bike;
import com.juanca.bikemicroservice.repository.IBikeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BikeService {
    
    @Autowired
    private IBikeRepository bikeRepository;
    
    public List<Bike> listBikers()
    {
      return bikeRepository.findAll();
    }
    
    
    public Bike insertBike(Bike bike)
    {
        return bikeRepository.save(bike);
    }
    
    public Bike getBikeById(Long id)
    {
       return bikeRepository.findById(id).orElse(null);
    }
    
    public List<Bike> listBikesByUserId(int userId)
    {
       return bikeRepository.findByUserId(userId);
    }
}
