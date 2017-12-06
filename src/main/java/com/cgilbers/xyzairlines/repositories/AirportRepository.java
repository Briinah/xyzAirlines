package com.cgilbers.xyzairlines.repositories;

import com.cgilbers.xyzairlines.models.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This class represents the airport repository
 * @author cgilbers
 */
@Repository
public interface AirportRepository extends CrudRepository<Airport, Long>{

    /**
     * Find an airport by the name of its location
     * @param city String the location of the airport
     * @return If exists: Airport entity with the specified location, else null
     */
    public Airport findOneByCity(String city);
}
