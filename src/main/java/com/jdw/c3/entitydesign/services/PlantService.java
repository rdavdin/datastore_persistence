package com.jdw.c3.entitydesign.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdw.c3.entitydesign.entities.Plant;
import com.jdw.c3.entitydesign.repository.PlantRepository;

@Service
public class PlantService {
    @Autowired
    private PlantRepository plantRepository;

    public Plant getPlantByName(String name){
        return plantRepository.findOneByName(name);
    }

    public Long savePlant(Plant plant){
        return plantRepository.save(plant).getId();
    }

    public Boolean checkPlantDelivered(Long plantId){
        // return plantRepository.deliveryCompleted(plantId);
        return plantRepository.existsPlantByIdAndDeliveryCompleted(plantId, true); 
    }

    public List<Plant> getCheaperPlants(BigDecimal price){
        return plantRepository.findAllByPriceLessThan(price);
    }
}
