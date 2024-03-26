package com.betrybe.agrix.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception farm not found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FarmNotFoundException extends RuntimeException {

  /**
   *Constructor.
   */
  public FarmNotFoundException(String message) {
    super(message);
  }
}
