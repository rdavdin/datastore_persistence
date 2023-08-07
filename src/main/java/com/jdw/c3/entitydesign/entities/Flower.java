package com.jdw.c3.entitydesign.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="flowers")
public class Flower extends Plant{
    private String color;

    public Flower() {
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
