package com.betrybe.agrix.controllers;

import com.betrybe.agrix.errors.CropNotFoundException;
import com.betrybe.agrix.errors.FarmNotFoundException;
import com.betrybe.agrix.errors.FertilizeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller Exception.
 */
@ControllerAdvice
public class ManagerExceptionController {

  /**
   * Exception Farm not found.
   */
  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handleFarmNotFoundException(
          FarmNotFoundException exception) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
  }

  /**
   * Exception Crop not found.
   */
  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handleCropNotFoundException(
          CropNotFoundException exception) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
  }

  /**
   * Exception Fertilizer not found.
   */
  @ExceptionHandler(FertilizeNotFoundException.class)
  public ResponseEntity<String> handleFertilizerNotFoundException(
          FertilizeNotFoundException exception) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
  }
}
