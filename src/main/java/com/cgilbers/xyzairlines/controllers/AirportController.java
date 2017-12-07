package com.cgilbers.xyzairlines.controllers;

import com.cgilbers.xyzairlines.exceptions.ObjectNotFoundException;
import com.cgilbers.xyzairlines.models.Airport;
import com.cgilbers.xyzairlines.models.Plane;
import com.cgilbers.xyzairlines.repositories.AirportRepository;
import com.cgilbers.xyzairlines.repositories.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the airport controller
 * @author cgilbers
 */
@RestController
@RequestMapping("api/airport")
public class AirportController {

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    PlaneRepository planeRepository;

    /**
     * This method gets all the airports in the repository
     * @return list of airports
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Airport> getAll(){
        return airportRepository.findAll();
    }

    /**
     * This methods gets all the airport without the one where the specified plane is already stationed
     * @param planeId
     * @return list of airports
     */
    @RequestMapping(value = "destinations/{planeId}", method = RequestMethod.GET)
    public Iterable<Airport> getAllAirportsWithoutPlane(@PathVariable long planeId){
        Plane plane = planeRepository.findOne(planeId);

        List<Airport> list = new ArrayList<>();

        for(Airport a : airportRepository.findAll()){
            if(!a.getPlanes().contains(plane)){
                list.add(a);
            }
        }

        return list;
    }

    /**
     * This method saves an airport to the airport repository
     * @param airport airport to be added
     * @return the added airport object
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Airport add(@RequestBody Airport airport){
        airportRepository.save(airport);

        return airport;
    }

    /**
     * This method gets an airport with a specific location
     * @param city string, the location of the airport
     * @return an airport object with specified location or throws an exception
     */
    @RequestMapping(value = "get/{city}", method = RequestMethod.GET)
    public Airport getByLocation(@PathVariable String city){

        Airport airport = airportRepository.findOneByCity(city);

        if(airport == null)
            throw new ObjectNotFoundException("The airport with location " + city + " could not be found");

        return airport;
    }

    /**
     * This method deletes an airport from the repository
     * @param id the airport to delete
     * @return the airport object
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public Airport delete(@PathVariable long id){

        Airport airport = airportRepository.findOne(id);

        if(airport == null)
            throw new ObjectNotFoundException("The airport could not be found");

        airportRepository.delete(id);

        return airport;
    }
}
