package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.service.ServiceCrop;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Crop.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {
  private ServiceCrop serviceCrop;

  @Autowired
  public CropController(ServiceCrop serviceCrop) {
    this.serviceCrop = serviceCrop;
  }

  /**
   * Get all crops.
   */
  @GetMapping()
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> cropList = serviceCrop.getAllCrop();
    List<CropDto> cropDtoList = cropList.stream()
            .map((crop) -> new CropDto(crop.getId(),
                    crop.getFarm().getId(), crop.getName(), crop.getPlantedArea(),
                    crop.getPlantedDate(), crop.getHarvestDate()))
            .collect(Collectors.toList());
    return ResponseEntity.ok(cropDtoList);
  }

  /**
   * Get Crop by id.
   */
  @GetMapping("/{cropId}")
  public ResponseEntity<CropDto> getCropsById(@PathVariable Long cropId) {
    Crop crop = serviceCrop.getCropById(cropId);
    CropDto cropDto = new CropDto(crop.getId(), crop.getFarm().getId(),
            crop.getName(), crop.getPlantedArea(),
            crop.getPlantedDate(), crop.getHarvestDate());
    return ResponseEntity.ok(cropDto);
  }


  /**
   * Search crops by date.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropsByHarvestDate(
          @RequestParam LocalDate start, @RequestParam LocalDate end) {
    List<Crop> cropList = serviceCrop.getCropByHarvestDate(start, end);
    List<CropDto> cropDtoList = cropList.stream()
            .map((crop) -> new CropDto(crop.getId(),
                    crop.getFarm().getId(), crop.getName(), crop.getPlantedArea(),
                    crop.getPlantedDate(), crop.getHarvestDate()))
            .collect(Collectors.toList());
    return ResponseEntity.ok(cropDtoList);
  }

  /**
   * Make Association Between Crops And Fertilizer.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> makeAssociationCropsFertilizer(@PathVariable Long cropId,
                                                               @PathVariable Long fertilizerId) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(serviceCrop.makaAssociationBetweenCropsAndFertilizer(cropId, fertilizerId));
  }

  /**
   * Get fertilizer by crop id.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizerByCropId(@PathVariable Long cropId) {
    List<Fertilizer> listFertilizers = serviceCrop.getFertilizersByIdCrop(cropId);
    return ResponseEntity.ok(listFertilizers.stream()
            .map(fertilizer -> new FertilizerDto(fertilizer.getId(),
                    fertilizer.getName(),
                    fertilizer.getBrand(),
                    fertilizer.getComposition())).collect(Collectors.toList()));
  }
}
