package com.example.test.controllers;

import com.example.test.entity.Animal;
import com.example.test.response.BaseResponse;
import com.example.test.services.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "ANIMALS", description = "CRUD ANIMALS")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage( String message ){
        kafkaTemplate.send("ReactTopic-2", message);
    }

    @KafkaListener( topics = "ReactTopic-1", groupId = "test_topics")
    public void sendMessageTwo( String message ){
        System.out.println( "SpringPro >>> " + message);
    }

    @RequestMapping( method = RequestMethod.GET, value = "/animals")
    @Operation( description = "Список всех питомцев", summary = "Список всех питомцев")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Found the animals", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
        @ApiResponse( responseCode = "400", description = "Bad request",       content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
        @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
    public BaseResponse getAll() throws Exception{
        try{
            BaseResponse response = new BaseResponse( 200, "success");
            response.setData( service.getAll() );
            sendMessage( "SpringPro --  method getAllAnimals Success ");
            return response;
        }catch ( Exception ex ){
            return BaseResponse.error( 999, ex );
        }
    }

    @RequestMapping( method = RequestMethod.GET, value = "/animal/{id}")
    @Operation( description = "Поиск питомца по ИД", summary = "Поиск питомца по ИД")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found the animal by ID", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
    public BaseResponse getFindById( Long id )  throws Exception{
        try{
            BaseResponse response = new BaseResponse( 200, "success");
            sendMessage( "SpringPro --  method getFindByIdAnimal Success ");
            response.setData( service.getById( id ));
            return response;
        } catch ( Exception ex ){
            return BaseResponse.error( 999, ex );
        }
    }

    @RequestMapping( method = RequestMethod.DELETE, value = "/animal/delete/{id}")
    @Operation( description = "Удаление питомца по ИД", summary = "Удаление питомца по ИД")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Delete animal", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
    public BaseResponse delete( Long id ) throws Exception{
        try {
            service.delAnimal( id );
            return BaseResponse.success();
        } catch ( Exception ex ){
            return  BaseResponse.error( 999, ex);
        }
    }

    @RequestMapping ( method = RequestMethod.PUT , value = "/animal/add")
    @Operation( description = "Создание питомца", summary = "Создание питомца")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Add animal", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
    public BaseResponse addAnimal( Animal animal ) throws Exception{
        try {
            sendMessage( "SpringPro --  method addAnimal Success ");
            service.addAnimal( animal );
            return  BaseResponse.success();
        } catch ( Exception ex ){
            return  BaseResponse.error( 999, ex );
        }
    }

    @RequestMapping( method = RequestMethod.POST, value = "/animal/change")
    @Operation( description = "Обновление питомца", summary = "Обновление питомца")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Success update animal", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
    public BaseResponse modyAnimal( Animal animal ) throws Exception{
        try {
            sendMessage( "SpringPro --  method modyAnimal Success ");
            service.modyAnimal( animal );
            return BaseResponse.success();
        } catch ( Exception ex ){
            return BaseResponse.error( 999, ex );
        }
    }

    @RequestMapping( method = RequestMethod.GET, value = "/animal/count")
    @Operation( description = "Количество питомцев", summary = "Количество питомцев")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Success", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
    public BaseResponse getCount(  ) throws Exception{
        try {
            BaseResponse response = new BaseResponse( 200, "success");
            sendMessage( "SpringPro --  method getCount Success  count: " + service.getCount() );
            response.setData( service.getCount() );
            return response;
        } catch ( Exception ex ){
            return BaseResponse.error( 999, ex );
        }
    }
}
