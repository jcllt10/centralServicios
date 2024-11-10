package com.juanca.carmicroservice.controller;

import com.juanca.carmicroservice.entity.Car;
import com.juanca.carmicroservice.service.CarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    
    @Autowired
    private CarService carService;
    
    @GetMapping
    public ResponseEntity<List<Car>> listarCars()
    {
        List<Car> cars = carService.listCars();
        
        if(cars.isEmpty())
            return ResponseEntity.noContent().build();
   
        return ResponseEntity.ok(cars);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Car> obtenerCarPorId(@PathVariable("id") Long id)
    {
       Car car = carService.getCarById(id);
       
       if(car == null)
           return ResponseEntity.noContent().build();
       
       return ResponseEntity.ok(car);
    }
    
    @PostMapping
    public ResponseEntity<Car> registrarCar(@RequestBody Car car)
    {
       Car carNew = carService.insertCar(car);
       
       return ResponseEntity.ok(carNew);
    }
    
    @GetMapping("/byuser/{idUser}")
    public ResponseEntity<List<Car>> listarCarsPorIdUser(@PathVariable("idUser") int idUser)
    {
       List<Car> carsPorIdUser = carService.listCarsByUserId(idUser);
       
       /*if(carsPorIdUser.isEmpty())
           return ResponseEntity.noContent().build();*/
       
       return ResponseEntity.ok(carsPorIdUser);
    }
    
}
