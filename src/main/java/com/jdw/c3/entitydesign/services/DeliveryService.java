package com.jdw.c3.entitydesign.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdw.c3.entitydesign.entities.Delivery;
import com.jdw.c3.entitydesign.pojos.RecipientAndPrice;
import com.jdw.c3.entitydesign.repository.DeliveryRepository;

@Service
public class DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    public Long save(Delivery delivery){
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        return delivery.getId();
    }

    public RecipientAndPrice getBill(Long deliveryId){
       return deliveryRepository.getBill(deliveryId);
    }

    public List<Delivery> getDeliveries(String name) {
        return deliveryRepository.getAllDeliveryByName(name);
    }
}
