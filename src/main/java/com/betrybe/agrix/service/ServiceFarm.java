package com.betrybe.agrix.service;

import com.betrybe.agrix.errors.FarmNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service Farm.
 */
@Service
public class ServiceFarm {
  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  /**
   *create farm.
   */
  @Autowired
  public ServiceFarm(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   *Update farm.
   */
  public Optional<Farm> updateFarm(Long id, Farm farm) throws FarmNotFoundException {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException("Fazenda não encontrada!");
    }
    Farm farmFromDb = optionalFarm.get();
    farmFromDb.setCrops(farm.getCrops());
    farmFromDb.setName(farm.getName());
    farmFromDb.setSize(farm.getSize());

    Farm upadateFarm = farmRepository.save(farmFromDb);
    return Optional.of(upadateFarm);
  }

  /**
   *Delete farm.
   */
  public void deleteFarm(Long id) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException("Fazenda não encontrada!");
    }

    farmRepository.deleteById(id);
  }

  /**
   *get by id farm.
   */
  public Optional<Farm> getFarmById(Long id) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException("Fazenda não encontrada!");
    }
    return Optional.of(optionalFarm.get());
  }

  /**
   *get all farm.
   */
  public List<Farm> getAllFarm() {
    return farmRepository.findAll();
  }

  /**
   * Service Create Crop In Farm.
   */
  public Crop insertCropInFarm(Long id, Crop crop) throws FarmNotFoundException {
    Optional<Farm> optionalFarm = farmRepository.findById(id);
    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException("Fazenda não encontrada!");
    }
    crop.setFarm(optionalFarm.get());
    Crop newCrop = cropRepository.save(crop);
    return newCrop;
  }

  /**
   *service get crop by id farm.
   */

  public List<Crop> getByCropIdFarm(Long id) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);
    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException("Fazenda não encontrada!");
    }
    List<Crop> cropList = cropRepository.findByFarmId(optionalFarm.get());

    return cropList;
  }
}
