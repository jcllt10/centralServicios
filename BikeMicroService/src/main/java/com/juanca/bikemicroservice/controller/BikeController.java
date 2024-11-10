package com.juanca.bikemicroservice.controller;

import com.juanca.bikemicroservice.entity.Bike;
import com.juanca.bikemicroservice.service.BikeService;
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
@RequestMapping("/api/bikes")
public class BikeController {
    
    @Autowired
    private BikeService bikeService;
    
    @GetMapping
    public ResponseEntity<List<Bike>> listarBikes()
    {
        List<Bike> bikes = bikeService.listBikers();
        
        if(bikes.isEmpty())
            return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok(bikes);
    }
    
    @PostMapping
    public ResponseEntity<Bike> registrarBike(@RequestBody Bike bike)
    {
       Bike bikeNew = bikeService.insertBike(bike);
       
       return ResponseEntity.ok(bikeNew);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Bike> buscarBikePorId(@PathVariable("id") Long id)
    {
        Bike bike = bikeService.getBikeById(id);
        
        if(bike == null)
            return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok(bike);
    }
    
    @GetMapping("/byuser/{idUser}")
    public ResponseEntity<List<Bike>> listarBikePorIdUser(@PathVariable("idUser") int idUser)
    {
        List<Bike> bikes = bikeService.listBikesByUserId(idUser);
        
      /*  if(bikes.isEmpty())
            return ResponseEntity.noContent().build();*/
        
        return ResponseEntity.ok(bikes);
        
    }
    
}
