package com.cgilbers.xyzairlines.models;

import javax.persistence.*;
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

    @Column(unique = true)
    @NotNull
    private String serialNumber;

    private String type;

    // Maximum capacity of fuel tank
    @NotNull
    private int maxFuel;

    // Current fuel level
    private int currentFuel;

    @NotNull
    private int consumptionRate;

    // Empty constructor for spring
    public Plane(){
    }

    public Plane(String serialNumber, int maxFuel, int currentFuel, int consumptionRate) {
        this.serialNumber = serialNumber;
        this.maxFuel = maxFuel;
        this.currentFuel = currentFuel;
        this.consumptionRate = consumptionRate;
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

        if(currentFuel < 0)
            this.currentFuel = 0;
        else if (currentFuel > maxFuel)
            this.currentFuel = maxFuel;
        else
            this.currentFuel = currentFuel;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getConsumptionRate() {
        return consumptionRate;
    }

    public void setConsumptionRate(int consumptionRate) {
        this.consumptionRate = consumptionRate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
