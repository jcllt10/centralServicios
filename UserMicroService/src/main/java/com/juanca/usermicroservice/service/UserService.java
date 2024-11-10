package com.juanca.usermicroservice.service;

import com.juanca.usermicroservice.entity.User;
import com.juanca.usermicroservice.feignclients.BikeFeignClient;
import com.juanca.usermicroservice.feignclients.CarFeignClient;
import com.juanca.usermicroservice.model.Bike;
import com.juanca.usermicroservice.model.Car;
import com.juanca.usermicroservice.repository.IUserRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private CarFeignClient carFeignClient;
    
    @Autowired
    private BikeFeignClient bikeFeignClient;
    
    public List<User> listUsers()
    {
       return userRepository.findAll();
    }
    
    public User getUserById(Long id)
    {
      return userRepository.findById(id).orElse(null);
    }
    
    public User insertUser(User user)
    {
       return userRepository.save(user);
    }
    
    public List<Car> getCars(Long idUser)
    {
      List<Car> carsByUser = restTemplate.getForObject("http://localhost:8002/api/cars/byuser/" + idUser, List.class);
      
      return carsByUser;
    }
    
    public List<Bike> getBikes(Long idUser)
    {
      List<Bike> bikesByUser = restTemplate.getForObject("http://localhost:8003/api/bikes/byuser/" + idUser, List.class);
       
       return bikesByUser;
    }
    
    public Car guardarCar(Car car,Long idUser)
    {
        car.setUserId(idUser);
        Car carNew = carFeignClient.registrarCar(car);
        
        return carNew;
    }
    
    public Bike guardarBike(Bike bike,Long idUser)
    {
       bike.setUserId(idUser);
       Bike bikeNew = bikeFeignClient.regBike(bike);
       
       return bikeNew;
    }
    
    public Map<String,Object> getUserAndVehicles(Long userId)
    {
        Map<String,Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        
        if(user == null){
             result.put("mensaje", "No existe el usuario enviado");
             return result;
        }
        result.put("User", user);
        
        List<Car> listCars = carFeignClient.listCarsByIdUser(userId);
        if(listCars.isEmpty())
            result.put("cars", "El usuario no tiene coches registrados");
        else
            result.put("cars",listCars);
        
        List<Bike> ListBikes = bikeFeignClient.listBikesByIdUser(userId);
        if(ListBikes.isEmpty())
            result.put("bikes", "El usuario no tiene bicicletas registradas");
        else
            result.put("bikes",ListBikes);
        
        return result;
    }
    
}

