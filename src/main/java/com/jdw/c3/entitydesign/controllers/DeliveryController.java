package com.jdw.c3.entitydesign.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jdw.c3.entitydesign.entities.Delivery;
import com.jdw.c3.entitydesign.pojos.RecipientAndPrice;
import com.jdw.c3.entitydesign.pojos.Views;
import com.jdw.c3.entitydesign.services.DeliveryService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public Long scheduleDelivery(@RequestBody Delivery delivery){
        return deliveryService.save(delivery);
    }

    @GetMapping("/bill/{deliveryId}")
    public RecipientAndPrice getBill(@PathVariable Long deliveryId) {
        return deliveryService.getBill(deliveryId);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/list/{name}")
    public List<Delivery> getDeliveries(@PathVariable String name) {
        return deliveryService.getDeliveries(name);
    }
}
