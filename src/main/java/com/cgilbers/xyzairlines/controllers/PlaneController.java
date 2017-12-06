package com.cgilbers.xyzairlines.controllers;

import com.cgilbers.xyzairlines.models.Plane;
import com.cgilbers.xyzairlines.repositories.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents the plane controller
 * @author cgilbers
 */
@RestController
@RequestMapping("api/plane")
public class PlaneController {

    @Autowired
    PlaneRepository planeRepository;

    /**
     * This method gets all the planes in the repository
     * @return list of planes
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Plane> getAll(){
        return planeRepository.findAll();
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
}