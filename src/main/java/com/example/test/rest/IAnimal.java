package com.example.test.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.test.entity.Animal;
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
@Tag( name = "2. ANIMALS", description = "CRUD ANIMALS" )
@RequestMapping( "animals" )
@ApiResponses( value = {
    @ApiResponse( responseCode = "200", description = "Success",            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
    @ApiResponse( responseCode = "400", description = "Bad request",        content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
    @ApiResponse( responseCode = "500", description = "System malfunction", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
public interface IAnimal {

    @RequestMapping( method = RequestMethod.GET, value = "/all")
    @Operation( description = "Список всех питомцев", summary = "Список всех питомцев")
    public ResponseEntity getAll() throws Exception;

    @RequestMapping( method = RequestMethod.GET, value = "/find/{id}")
    @Operation( description = "Поиск питомца по ИД", summary = "Поиск питомца по ИД")
    public ResponseEntity getFindById( @RequestParam Long id )  throws Exception;

    @RequestMapping( method = RequestMethod.DELETE, value = "/delete/{id}")
    @Operation( description = "Удаление питомца по ИД", summary = "Удаление питомца по ИД")
    public ResponseEntity delete( @RequestParam Long id ) throws Exception;

    @RequestMapping ( method = RequestMethod.PUT , value = "/add")
    @Operation( description = "Создание питомца", summary = "Создание питомца")
    public ResponseEntity addAnimal( @RequestBody  Animal animal ) throws Exception;

    @RequestMapping( method = RequestMethod.POST, value = "/change")
    @Operation( description = "Обновление питомца", summary = "Обновление питомца")
    public ResponseEntity modyAnimal( @RequestBody Animal animal ) throws Exception;

    @RequestMapping( method = RequestMethod.GET, value = "/count")
    @Operation( description = "Количество питомцев", summary = "Количество питомцев")
    public ResponseEntity getCount() throws Exception;
    
}
