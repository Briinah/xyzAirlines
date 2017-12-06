package com.cgilbers.xyzairlines.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * This class represents a plane
 * @author cgilbers
 */
@Entity
public class Plane {

    // Unique id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Maximum capacity of fuel tank
    @NotNull
    private int maxFuel;

    // Current fuel level
    @NotNull
    private int currentFuel;

    // Empty constructor for spring
    public Plane(){
        maxFuel = 5;
    }

    // Constructor for testing
    public Plane(int maxFuel, int currentFuel) {
        this.maxFuel = maxFuel;
        this.currentFuel = currentFuel;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }

    public int getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(int currentFuel) {
        this.currentFuel = currentFuel;
    }
}
