package com.jdw.c3.entitydesign.services;

import org.springframework.stereotype.Service;

import com.jdw.c3.entitydesign.entities.Plant;

@Service
public class PlantService {
    public Plant getPlantByName(String name){
        return new Plant();
    }
}
