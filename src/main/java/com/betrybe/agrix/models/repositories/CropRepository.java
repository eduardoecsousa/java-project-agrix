package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository crop.
 */

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
  List<Crop> findByFarmId(Farm farmId);
}
