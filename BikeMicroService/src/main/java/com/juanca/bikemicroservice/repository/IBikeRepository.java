package com.juanca.bikemicroservice.repository;

import com.juanca.bikemicroservice.entity.Bike;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBikeRepository extends JpaRepository<Bike,Long>{
    
    public List<Bike> findByUserId(int userId);
    
}
