package com.betrybe.agrix.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception crop not found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CropNotFoundException extends RuntimeException {
  public CropNotFoundException(String message) {
    super(message);
  }
}
