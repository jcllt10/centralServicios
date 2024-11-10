package com.juanca.usermicroservice.feignclients;

import com.juanca.usermicroservice.model.Bike;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="BikeMicroService",url = "http://localhost:8003/api/bikes")
public interface BikeFeignClient {
    
    @PostMapping()
    public Bike regBike(@RequestBody Bike user);
    
    @GetMapping("/byuser/{idUser}")
    List<Bike> listBikesByIdUser(@PathVariable("idUser") Long idUser);
}
