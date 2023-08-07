package com.jdw.c3.entitydesign.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Shrub")
public class Shrub extends Plant{
    private Float width;
    private Float height;

    public Shrub() {
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    
}
