package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropCreateDto;
import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.service.ServiceFarm;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Farm.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {
  private ServiceFarm serviceFarm;

  @Autowired
  public FarmController(ServiceFarm serviceFarm) {
    this.serviceFarm = serviceFarm;
  }

  @PostMapping()
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = serviceFarm.insertFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  @GetMapping()
  public ResponseEntity<List<Farm>> getAllFarm() {
    return ResponseEntity.ok(serviceFarm.getAllFarm());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Farm> getByIdFarm(@PathVariable Long id) {
    Optional<Farm> optionalFarm = serviceFarm.getFarmById(id);
    return ResponseEntity.ok(optionalFarm.get());
  }

  /**
   * create a plantation for a farm.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createNewCropInFarm(
          @PathVariable Long farmId, @RequestBody CropCreateDto cropCreateDto) {
    Crop newCrop = serviceFarm.insertCropInFarm(farmId, cropCreateDto.toCrop());

    CropDto cropDto1 = new CropDto(newCrop.getId(), newCrop.getFarm().getId(),
            newCrop.getName(), newCrop.getPlantedArea(),
            newCrop.getPlantedDate(), newCrop.getHarvestDate());

    return ResponseEntity.status(HttpStatus.CREATED).body(cropDto1);
  }

  /**
   *take crop by id farm.
   */
  @GetMapping("{farmId}/crops")
  public ResponseEntity<List<CropDto>> getFindByFarmCorp(@PathVariable Long farmId) {
    List<Crop> cropList = serviceFarm.getByCropIdFarm(farmId);
    List<CropDto> cropDtoList = cropList.stream()
            .map((crop) -> new CropDto(crop.getId(),
                    crop.getFarm().getId(), crop.getName(), crop.getPlantedArea(),
                    crop.getPlantedDate(), crop.getHarvestDate()))
            .collect(Collectors.toList());
    return ResponseEntity.ok(cropDtoList);
  }
}
