package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Fertilizer.
 */
@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
  List<Fertilizer> findByCrops(Crop crops);
}
