package com.betrybe.agrix.service;

import com.betrybe.agrix.errors.FertilizeNotFoundException;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Fertilizer.
 */
@Service
public class ServiceFertilizer {

  private FertilizerRepository fertilizerRepository;

  @Autowired
  public ServiceFertilizer(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Insert Fertilizer.
   */
  public Fertilizer insertFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * Get all Fertilizer.
   */
  public List<Fertilizer> getAllFertilizer() {
    return fertilizerRepository.findAll();
  }

  /**
   * Get Fertilizer by Id.
   */
  public Optional<Fertilizer> getFertilizerById(Long id) {
    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(id);
    if (optionalFertilizer.isEmpty()) {
      throw new FertilizeNotFoundException("Fertilizante não encontrado!");
    }

    return Optional.of(optionalFertilizer.get());
  }
}
