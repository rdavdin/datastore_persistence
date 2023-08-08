package com.jdw.c3.entitydesign.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jdw.c3.entitydesign.entities.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long>{
    //way 1 using query
    @Query("SELECT p.delivery.completed FROM Plant p WHERE id = :id")
    public Boolean deliveryCompleted(Long id);

    //way 2 using named query
    public Boolean plantDelivered(Long id);

    //way 3 returning a wrapper class 
    @Query("SELECT new java.lang.Boolean(p.delivery.completed) FROM Plant p WHERE p.id = :id")
    public Boolean isPlantDeliveryCompleted(Long id);

    //way 4 using exists...By
    //check if a plant by this id exists where delivery has been completed ???
    public Boolean existsPlantByIdAndDeliveryCompleted(Long id, Boolean delivered);

    public List<Plant> findAllByPriceLessThan(BigDecimal price);

    public Plant findOneByName(String name);
}
