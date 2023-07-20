package com.example.test.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.test.entity.Person;
import com.example.test.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement( name = "Bearer Authentication" )
@Tag( name = "3. PERSONS", description = "CRUD PERSONS" )
@RequestMapping( "persons" )
@ApiResponses(value = {
    @ApiResponse( responseCode = "200", description = "Success",            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
    @ApiResponse( responseCode = "400", description = "Bad request",        content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
    @ApiResponse( responseCode = "500", description = "System malfunction", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
public interface IPerson {

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @Operation( description = "Список всех людей", summary = "Список всех людей")
    public ResponseEntity getAllPerson();

    @RequestMapping( method = RequestMethod.GET, value = "/find/{id}")
    @Operation( description = "Поиск человека по ИД", summary = "Поиск человека по ИД")

    public ResponseEntity findByIdPerson( @RequestParam Long id ) throws Exception;

    @RequestMapping( method = RequestMethod.PUT, value = "/add")
    @Operation( description = "Добавить человека", summary = "Добавить человека")
    public ResponseEntity savePerson( @RequestBody Person person ) throws Exception;

    @RequestMapping( method = RequestMethod.POST, value = "/update")
    @Operation( description = "Обновить человека", summary = "Обновить человека")
    public ResponseEntity updatePerson( @RequestBody Person person ) throws Exception;

    @RequestMapping( method = RequestMethod.DELETE, value = "/delete")
    @Operation( description = "Удалить человека по ИД", summary = "Удалить человека по ИД")
    public ResponseEntity deletePerson( @RequestParam Long id ) throws Exception;
    
}
