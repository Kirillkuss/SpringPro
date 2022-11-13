package com.example.test.controllers;

import com.example.test.entity.Animal;
import com.example.test.services.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimalController {

    @Autowired
    private AnimalService service;

    @RequestMapping( method = RequestMethod.GET, value = "/animals")
    @Operation( description = "Список всех питомцев", summary = "Список всех питомцев")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200" , description = "Found the animals", content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public List<Animal> getAll() throws Exception{
        return service.getAll();
    }

    @RequestMapping( method = RequestMethod.GET, value = "/animal/{id}")
    @Operation( description = "Поиск питомца по ИД", summary = "Поиск питомца по ИД")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found the animal by ID", content = { @Content(mediaType = "application/json")}),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Animal getFindById( Long id )  throws Exception{
        return service.getById( id );
    }

    @RequestMapping( method = RequestMethod.DELETE, value = "/animal/delete/{id}")
    @Operation( description = "Удаление питомца по ИД", summary = "Удаление питомца по ИД")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Delete animal", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public boolean delete( Long id ) throws Exception{
        return service.delAnimal( id );
    }

    @RequestMapping ( method = RequestMethod.PUT , value = "/animal/add")
    @Operation( description = "Создание питомца", summary = "Создание питомца")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Add animal", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public boolean addAnimal( Animal animal ) throws Exception{
        return service.addAnimal( animal );
    }

    @RequestMapping( method = RequestMethod.POST, value = "/animal/change")
    @Operation( description = "Обновление питомца", summary = "Обновление питомца")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Success update animal", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public boolean modyAnimal( Animal animal ) throws Exception{
        return service.modyAnimal( animal );
    }
}
