package com.juanca.usermicroservice.controller;

import com.juanca.usermicroservice.entity.User;
import com.juanca.usermicroservice.model.Bike;
import com.juanca.usermicroservice.model.Car;
import com.juanca.usermicroservice.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    
    @GetMapping
    public ResponseEntity<List<User>> listarUsuarios()
    {
        List<User> users = userService.listUsers();
        
        if(users.isEmpty())
           return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> obtenerUserPorId(@PathVariable("id") Long id)
    {
        User user = userService.getUserById(id);
        
        if(user == null)
           return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok(user);
    }
    
    @PostMapping
    public ResponseEntity<User> registrarUsuario(@RequestBody User user)
    {
      User userNew= userService.insertUser(user);
        
      return ResponseEntity.ok(userNew);
    }
    
    @GetMapping("/cars/{idUser}")
    public ResponseEntity<List<Car>> listarCarsPorIdUser(@PathVariable("idUser") Long idUser)
    {
       User user = userService.getUserById(idUser);
       
       if(user == null)
          return ResponseEntity.notFound().build();
       
       List<Car> cars = userService.getCars(idUser);
       
       return ResponseEntity.ok(cars);
    }
    
    @GetMapping("/bikes/{idUser}")
    public ResponseEntity<List<Bike>> listarBikesPorIdUser(@PathVariable("idUser") Long idUser)
    {
       User user = userService.getUserById(idUser);
       
       if(user == null)
          return ResponseEntity.notFound().build();
       
       List<Bike> bikes = userService.getBikes(idUser);
       
       if(bikes == null)
           return ResponseEntity.notFound().build();
       
       return ResponseEntity.ok(bikes);
    }
    
    
    @PostMapping("/savecar/{idUser}")
    public ResponseEntity<Car> guardarCar(@RequestBody Car car,@PathVariable("idUser") Long idUser)
    {
       if(userService.getUserById(idUser)==null)
           return ResponseEntity.notFound().build();
        
       Car carNew = userService.guardarCar(car, idUser);
       
       return ResponseEntity.ok(carNew);
    }
    
    @PostMapping("/savebike/{idUser}")
    public ResponseEntity<Bike> guardarBike(@RequestBody Bike bike,@PathVariable("idUser") Long idUser)
    {
       if(userService.getUserById(idUser)==null)
           return ResponseEntity.notFound().build();
        
       Bike bikeNew = userService.guardarBike(bike,idUser);
       
       return ResponseEntity.ok(bikeNew);
    }
    
    
    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String,Object>> listarTodosLosVehiculosPorIdUser(@PathVariable("userId") Long idUser)
    {
       Map<String,Object> resultAllVehiculos = userService.getUserAndVehicles(idUser);
       
       return ResponseEntity.ok(resultAllVehiculos);
    }
}
