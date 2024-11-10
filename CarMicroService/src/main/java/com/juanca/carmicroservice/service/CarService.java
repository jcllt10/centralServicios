package com.juanca.carmicroservice.service;

import com.juanca.carmicroservice.entity.Car;
import com.juanca.carmicroservice.repository.ICarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    
    @Autowired
    private ICarRepository carRepository;
    
    public List<Car> listCars()
    {
       return carRepository.findAll();
    }
    
    public Car getCarById(Long id)
    {
      return carRepository.findById(id).orElse(null);
    }
    
    public Car insertCar(Car car)
    {
      return carRepository.save(car);
    }
    
    public List<Car> listCarsByUserId(int userId)
    {
       return carRepository.findByUserId(userId);
    }
    
}
