package com.juanca.usermicroservice.feignclients;

import com.juanca.usermicroservice.model.Car;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CarMicroService",url = "http://localhost:8002/api/cars")
//@RequestMapping("/cars")
public interface CarFeignClient {
    
    @PostMapping()
    Car registrarCar(@RequestBody Car car);
    
    @GetMapping("/byuser/{idUser}")
    List<Car> listCarsByIdUser(@PathVariable("idUser") Long idUser);
    
}
