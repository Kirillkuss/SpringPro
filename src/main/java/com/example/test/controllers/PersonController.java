package com.example.test.controllers;

import com.example.test.entity.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.test.services.PersonService;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, value = "/person")
    @Operation( description = "Список всех людей", summary = "Список всех людей")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found the people", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public List<Person> getAllPerson() {
        return personService.findAll();
    }
    @RequestMapping( method = RequestMethod.GET, value = "/person/{id}")
    @Operation( description = "Поиск человека по ИД", summary = "Поиск человека по ИД")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found the people for ID", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Person findByIdPerson( Long id ) throws Exception{
        return personService.getPersonById( id );
    }
    @RequestMapping( method = RequestMethod.PUT, value = "/person/add")
    @Operation( description = "Добавить человека", summary = "Добавить человека")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Success save", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public void savePerson( Person person ) throws Exception{
        personService.savePerson( person );
    }
    @RequestMapping( method = RequestMethod.POST, value = "/person/update")
    @Operation( description = "Обновить человека", summary = "Обновить человека")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Success update", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public void updatePerson( Person person ) throws Exception{
        personService.updatePerson( person);
    }
    @RequestMapping( method = RequestMethod.DELETE, value = "/person/delete")
    @Operation( description = "Удалить человека по ИД", summary = "Удалить человека по ИД")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Success delete", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public void deletePerson( Long id ) throws Exception{
        personService.deletePerson( id );
    }

}
