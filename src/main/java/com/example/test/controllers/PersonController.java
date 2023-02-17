package com.example.test.controllers;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.test.services.PersonService;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "PERSONS", description = "CRUD PERSONS")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, value = "/person")
    @Operation( description = "Список всех людей", summary = "Список всех людей")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found the people", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
    public BaseResponse getAllPerson() {
        try {
            BaseResponse response = new BaseResponse( 200, "success" );
            response.setData( personService.findAllTwo() );
            return response;
        } catch ( Exception ex ){
            return BaseResponse.error( 999, ex );
        }
    }

    @RequestMapping( method = RequestMethod.GET, value = "/person/{id}")
    @Operation( description = "Поиск человека по ИД", summary = "Поиск человека по ИД")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found the people for ID", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
    public BaseResponse findByIdPerson( Long id ) throws Exception{
        try {
            BaseResponse response = new BaseResponse( 200, "success" );
            response.setData(personService.getPersonById( id ));
            return response;
        } catch ( Exception ex ){
            return BaseResponse.error( 999, ex );
        }
    }

    @RequestMapping( method = RequestMethod.PUT, value = "/person/add")
    @Operation( description = "Добавить человека", summary = "Добавить человека")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Success save", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
    public BaseResponse savePerson( Person person ) throws Exception{
        try {
            personService.savePerson( person );
            return BaseResponse.success();
        }catch ( Exception ex ){
            return BaseResponse.error( 999, ex );
        }
    }

    @RequestMapping( method = RequestMethod.POST, value = "/person/update")
    @Operation( description = "Обновить человека", summary = "Обновить человека")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Success update", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
    public BaseResponse updatePerson( Person person ) throws Exception{
        try {
            personService.updatePerson( person) ;
            return BaseResponse.success();
        }catch ( Exception ex ) {
            return BaseResponse.error(999, ex);
        }
    }

    @RequestMapping( method = RequestMethod.DELETE, value = "/person/delete")
    @Operation( description = "Удалить человека по ИД", summary = "Удалить человека по ИД")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Success delete", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
    public BaseResponse deletePerson( Long id ) throws Exception{
        try {
            personService.deletePerson( id );
            return BaseResponse.success();
        } catch ( Exception ex ){
            return BaseResponse.error( 999, ex );
        }
    }

}
