package com.betrybe.agrix.errors;

/**
 * Exception Fertilizer not found.
 */
public class FertilizeNotFoundException extends RuntimeException {
  /**
   * Constructor.
   */
  public FertilizeNotFoundException(String message) {
    super(message);
  }
}
