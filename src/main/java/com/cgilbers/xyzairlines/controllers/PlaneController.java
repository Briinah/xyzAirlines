package com.cgilbers.xyzairlines.controllers;

import com.cgilbers.xyzairlines.exceptions.IncorrectDestinationException;
import com.cgilbers.xyzairlines.exceptions.NotEnoughFuelException;
import com.cgilbers.xyzairlines.models.Airport;
import com.cgilbers.xyzairlines.models.Plane;
import com.cgilbers.xyzairlines.repositories.AirportRepository;
import com.cgilbers.xyzairlines.repositories.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the plane controller
 * @author cgilbers
 */
@RestController
@RequestMapping("api/plane")
public class PlaneController {

    @Autowired
    PlaneRepository planeRepository;

    @Autowired
    AirportRepository airportRepository;

    /**
     * This method gets all the planes in the repository
     * @return list of planes
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Plane> getAll(){
        return planeRepository.findAll();
    }

    /**
     * This method gets all planes that are not yet on an airport
     * @return list of planes
     */
    @RequestMapping(value = "stationary", method = RequestMethod.GET)
    public Iterable<Plane> getStationary(){

        List<Plane> list = new ArrayList<>();

        for(Plane plane : planeRepository.findAll()) {
            boolean free = true;
            for (Airport airport : airportRepository.findAll()) {
                if(airport.getPlanes().contains(plane))
                    free = false;
            }

            if(free)
                list.add(plane);
        }

        return list;
    }

    /**
     * This method saves a plane to the plane repository
     * @param plane plane to be added
     * @return the added plane object
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Plane add(@RequestBody Plane plane){
        planeRepository.save(plane);

        return plane;
    }

    /**
     * This method sets the current fuel level of a plane at max level
     * @param id id of the plane
     * @return plane object
     */
    @RequestMapping(value = "tank/{id}", method = RequestMethod.PUT)
    public Plane tank(@PathVariable long id){
        Plane plane = planeRepository.findOne(id);

        plane.setCurrentFuel(plane.getMaxFuel());

        planeRepository.save(plane);

        return plane;
    }

    /**
     * This method adds the plane to the destination airport and removes 2 fuel from the plane
     * @param id
     * @param location
     * @return
     */
    @RequestMapping(value = "fly/{id}/{location}", method = RequestMethod.PUT)
    public Plane fly(@PathVariable long id, @PathVariable long location){
        Airport destination = airportRepository.findOne(location);
        Plane plane = planeRepository.findOne(id);

        if(plane.getCurrentFuel() < plane.getConsumptionRate()) {
            System.out.println("Plane needs to tank!");
            throw new NotEnoughFuelException("Plane id: " + id + " does not have enough fuel");
        }

        if(destination.getPlanes().contains(plane)) {
            System.out.println("Plane is already at the destination!");
            throw new IncorrectDestinationException();
        }

        for(Airport airport: airportRepository.findAll()){
            if(airport.getPlanes().contains(plane)) {
                airport.getPlanes().remove(plane);
                airportRepository.save(airport);
            }
        }

        plane.setCurrentFuel(plane.getCurrentFuel() - plane.getConsumptionRate());
        destination.getPlanes().add(plane);

        planeRepository.save(plane);
        airportRepository.save(destination);

        return plane;

    }

}
