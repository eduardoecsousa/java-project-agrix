package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.PersonCreateDto;
import com.betrybe.agrix.controllers.dto.PersonDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Person Controller.
 */
@RestController
@RequestMapping(value = "/persons")
public class PersonController {
  private PersonService personService;

  /**
   * Constructor.
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Register person.
   */
  @PostMapping()
  public ResponseEntity<PersonDto> registerPerson(@RequestBody PersonCreateDto personCreateDto) {
    Person newPerson = personService.create(personCreateDto.toPerson());
    return ResponseEntity.status(HttpStatus.CREATED).body(
            new PersonDto(newPerson.getId(), newPerson.getUsername(), newPerson.getRole())
    );
  }
}
