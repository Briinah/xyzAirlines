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
    private String city;

    // The name of the airport
    @NotNull
    private String name;

    // A list of planes currently at the airport
    @OneToMany
    private List<Plane> planes;

    // empty constructor for spring
    public Airport(){}

    public Airport(String city, String name, List<Plane> planes) {
        this.city = city;
        this.name = name;
        this.planes = planes;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }
}
