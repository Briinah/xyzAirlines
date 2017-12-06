package com.cgilbers.xyzairlines.repositories;

import com.cgilbers.xyzairlines.models.Plane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This class represents the plane repository
 * @author cgilbers
 */
@Repository
public interface PlaneRepository extends CrudRepository<Plane, Long>{
}
