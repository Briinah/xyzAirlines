package com.cgilbers.xyzairlines.controllers;

import com.cgilbers.xyzairlines.exceptions.ObjectNotFoundException;
import com.cgilbers.xyzairlines.models.Airport;
import com.cgilbers.xyzairlines.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents the airport controller
 * @author cgilbers
 */
@RestController
@RequestMapping("api/airport")
public class AirportController {

    @Autowired
    AirportRepository airportRepository;

    /**
     * This method gets all the airports in the repository
     * @return list of airports
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Airport> getAll(){
        return airportRepository.findAll();
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
     * @param location string, the location of the airport
     * @return an airport object with specified location or throws an exception
     */
    @RequestMapping(value = "get/{location}", method = RequestMethod.GET)
    public Airport getByLocation(@PathVariable String location){

        Airport airport = airportRepository.findOneByLocation(location);

        if(airport == null)
            throw new ObjectNotFoundException("The airport with location " + location + " could not be found");

        return airport;
    }
}
