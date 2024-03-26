package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.util.security.Role;

/**
 * Person DTO.
 */
public record PersonDto(Long id, String username, Role role) {
}
