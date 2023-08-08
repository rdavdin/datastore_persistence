package com.jdw.c3.entitydesign.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jdw.c3.entitydesign.entities.Delivery;
import com.jdw.c3.entitydesign.entities.Plant;
import com.jdw.c3.entitydesign.pojos.RecipientAndPrice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class DeliveryRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Delivery delivery){
        entityManager.persist(delivery);
    }

    public Delivery find(Long id){
        return entityManager.find(Delivery.class, id);
    }

    public Delivery merge(Delivery delivery){
        return entityManager.merge(delivery);
    }

    public void delete(Long id){
        Delivery delivery = entityManager.find(Delivery.class, id);
        entityManager.remove(delivery);
    }

    public List<Delivery> getAllDeliveryByName(String recipientName){
        TypedQuery<Delivery> query = entityManager.createNamedQuery("Delivery.getAllDeliveryByName", Delivery.class);
        query.setParameter("recipientName", recipientName);
        return query.getResultList();
    }

    public RecipientAndPrice getBill(Long deliveryId){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPrice> criteria = builder.createQuery(RecipientAndPrice.class);
        Root<Plant> root = criteria.from(Plant.class);
        
        criteria.select(builder.construct(RecipientAndPrice.class,
                                            root.get("delivery").get("recipientName"),
                                            builder.sum(root.get("price"))
                                ));
        criteria.where(builder.equal(root.get("delivery").get("id"), deliveryId));

        return entityManager.createQuery(criteria).getSingleResult();
    }
}
