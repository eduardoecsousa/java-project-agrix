package com.betrybe.agrix.service;

import com.betrybe.agrix.errors.CropNotFoundException;
import com.betrybe.agrix.errors.FertilizeNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Crop.
 */
@Service
public class ServiceCrop {
  private CropRepository cropRepository;

  private FertilizerRepository fertilizerRepository;

  /**
   * Constructor.
   */
  @Autowired
  public ServiceCrop(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;

  }

  /**
   * get all crops.
   */
  public List<Crop> getAllCrop() {
    return cropRepository.findAll();
  }

  /**
   * get crops by id.
   */
  public Crop getCropById(Long id) {
    Optional<Crop> cropOptional = cropRepository.findById(id);
    if (cropOptional.isEmpty()) {
      throw new CropNotFoundException("Plantação não encontrada!");
    }
    return cropOptional.get();
  }

  /**
   * Search crops by date.
   */
  public List<Crop> getCropByHarvestDate(LocalDate start, LocalDate end) {

    List<Crop> cropList = cropRepository.findAll();

    return cropList.stream().filter(crop -> start.isBefore(crop.getHarvestDate()))
            .filter(crop -> end.isAfter(crop.getHarvestDate()))
            .toList();
  }

  /**
   * Make Association Between Crops And Fertilizer.
   */

  public String makaAssociationBetweenCropsAndFertilizer(Long cropId, Long fertilizerId) {
    Optional<Crop> cropOptional = cropRepository.findById(cropId);
    if (cropOptional.isEmpty()) {
      throw new CropNotFoundException("Plantação não encontrada!");
    }

    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(fertilizerId);
    if (optionalFertilizer.isEmpty()) {
      throw new FertilizeNotFoundException("Fertilizante não encontrado!");
    }

    Crop crop = cropOptional.get();
    Fertilizer fertilizer = optionalFertilizer.get();
    crop.getFertilizers().add(fertilizer);
    fertilizer.getCrops().add(crop);
    cropRepository.save(crop);
    fertilizerRepository.save(fertilizer);

    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * Get Fertilizer by Crop id.
   */
  public List<Fertilizer> getFertilizersByIdCrop(Long cropId) {
    Optional<Crop> cropOptional = cropRepository.findById(cropId);
    if (cropOptional.isEmpty()) {
      throw new CropNotFoundException("Plantação não encontrada!");
    }
    Crop crop = cropOptional.get();
    return fertilizerRepository.findByCrops(crop);
  }
}
