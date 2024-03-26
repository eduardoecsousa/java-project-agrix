package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.util.security.Role;

/**
 * Person create DTO.
 */
public record PersonCreateDto(Long id, String username, String password, Role role) {
  /**
   * Create Person.
   */
  public Person toPerson() {
    return new Person(id, username, password, role);
  }
}
