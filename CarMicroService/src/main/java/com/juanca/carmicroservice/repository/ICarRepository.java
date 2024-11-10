package com.juanca.carmicroservice.repository;

import com.juanca.carmicroservice.entity.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepository extends JpaRepository<Car,Long>{
    
    List<Car> findByUserId(int userId);
    
}
