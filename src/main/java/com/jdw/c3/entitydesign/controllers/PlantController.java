package com.jdw.c3.entitydesign.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jdw.c3.entitydesign.entities.Plant;
import com.jdw.c3.entitydesign.pojos.PlantDTO;
import com.jdw.c3.entitydesign.pojos.Views;
import com.jdw.c3.entitydesign.services.PlantService;

@RestController
@RequestMapping("/plant")
public class PlantController {
    
    @Autowired
    private PlantService plantService;


    @GetMapping
    public PlantDTO getPlantDTO(String name){
        Plant plant = plantService.getPlantByName(name);
        return convertPlant2DTO(plant);
    }

    @JsonView(Views.Public.class)
    public Plant getFilteredPlant(String name){
        return plantService.getPlantByName(name);
    }

    //Entity to/from DTOs
    public PlantDTO convertPlant2DTO(Plant plant){
        // return new PlantDTO(plant.getName(), plant.getPrice());
        PlantDTO plantDTO = new PlantDTO();
        BeanUtils.copyProperties(plant, plantDTO);
        return plantDTO;
    }

    // public Plant convertDTO2Plant(PlantDTO plantDTO){
    //     //do sth
    //     return new Plant();
    // }
}
