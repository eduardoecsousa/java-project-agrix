package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerCreateDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.service.ServiceFertilizer;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Fertilizer.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {

  private ServiceFertilizer serviceFertilizer;

  @Autowired
  public FertilizerController(ServiceFertilizer serviceFertilizer) {
    this.serviceFertilizer = serviceFertilizer;
  }

  /**
   * Crete Fertilizer.
   */
  @PostMapping()
  public ResponseEntity<FertilizerDto> createFertilizer(
          @RequestBody FertilizerCreateDto fertilizerCreateDto) {
    Fertilizer newFertilizer = serviceFertilizer.insertFertilizer(
            fertilizerCreateDto.toFertilizer());
    FertilizerDto fertilizerDto = new FertilizerDto(
            newFertilizer.getId(),
            newFertilizer.getName(),
            newFertilizer.getBrand(),
            newFertilizer.getComposition());
    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizerDto);
  }

  /**
   * Get all Fertilizer.
   */
  @GetMapping()
  @Secured("ADMIN")
  public ResponseEntity<List<FertilizerDto>> getAllFertilizer() {
    List<Fertilizer> fertilizerList = serviceFertilizer.getAllFertilizer();
    return ResponseEntity.ok(fertilizerList.stream()
            .map(fertilizer -> new FertilizerDto(fertilizer.getId(),
                    fertilizer.getName(),
                    fertilizer.getBrand(),
                    fertilizer.getComposition())).collect(Collectors.toList()));
  }

  /**
   * Get Fertilizer by id.
   */

  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long id) {
    Optional<Fertilizer> optionalFertilizer = serviceFertilizer.getFertilizerById(id);
    FertilizerDto fertilizerDto = new FertilizerDto(
            optionalFertilizer.get().getId(),
            optionalFertilizer.get().getName(),
            optionalFertilizer.get().getBrand(),
            optionalFertilizer.get().getComposition()
    );
    return ResponseEntity.ok(fertilizerDto);
  }
}
