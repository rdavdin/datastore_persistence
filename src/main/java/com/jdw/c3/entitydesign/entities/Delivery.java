package com.jdw.c3.entitydesign.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Nationalized;
import org.hibernate.type.YesNoConverter;

import com.fasterxml.jackson.annotation.JsonView;
import com.jdw.c3.entitydesign.pojos.Views;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
@NamedQuery(
    name = "Delivery.getAllDeliveryByName",
    query = "SELECT d FROM Delivery d WHERE d.recipientName = :recipientName"
)
@Entity
public class Delivery {
    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue
    private Long id;

    @JsonView(Views.Public.class)
    @Nationalized
    private String recipientName;

    @JsonView(Views.Public.class)
    @Column(name="address_full", length=500)
    private String address;

    private LocalDateTime deliveryTime;

    @JsonView(Views.Public.class)
    @Convert(converter = YesNoConverter.class)
    private Boolean completed = false;

    //make sure to specify mappedBy. Lazy fetch optional,
   //  but often a good idea for collection attributes
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Plant> plants;

    public Delivery(){}

    

    public Delivery(String recipientName, String address, LocalDateTime deliveryTime, Boolean completed) {
        this.recipientName = recipientName;
        this.address = address;
        this.deliveryTime = deliveryTime;
        this.completed = completed;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean isCompleted) {
        this.completed = isCompleted;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
