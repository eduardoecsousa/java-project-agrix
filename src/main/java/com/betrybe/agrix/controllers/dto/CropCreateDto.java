package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Fertilizer;
import java.time.LocalDate;
import java.util.List;

/**
 *create crop DTO.
 */
public record CropCreateDto(Long id,
                            Farm farmId,
                            String name,
                            Double plantedArea,
                            LocalDate plantedDate,
                            LocalDate harvestDate, List<Fertilizer> fertilizers) {
  public Crop toCrop() {
    return new Crop(id, farmId, name, plantedArea, plantedDate, harvestDate, fertilizers);
  }
}
