package com.betrybe.agrix.controllers.dto;

import java.time.LocalDate;

/**
 *Crop DTO.
 */
public record CropDto(Long id,
                      Long farmId,
                      String name,
                      Double plantedArea,
                      LocalDate plantedDate,
                      LocalDate harvestDate) {
}
