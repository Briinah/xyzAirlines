package com.cgilbers.xyzairlines.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * This class represents an airport
 * @author cgilbers
 */
@Entity
public class Airport {

    // Unique id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // The location of the airport
    @NotNull
    private String location;

    // A list of planes currently at the airport
    @OneToMany
    private List<Plane> planes;

    // empty constructor for spring
    public Airport(){}

    // constructor for testing
    public Airport(String location, List<Plane> planes) {
        this.location = location;
        this.planes = planes;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }
}
