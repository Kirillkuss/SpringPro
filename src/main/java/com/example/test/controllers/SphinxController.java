package com.example.test.controllers;

import com.example.test.entity.Animal;
import com.example.test.services.SphinxDemo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SphinxController {

    @RequestMapping( method = RequestMethod.POST, value = "/{record}")
    @Operation( description = "record", summary = "Запись")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Record", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public String getRecord( String record) throws Exception{
        return SphinxDemo.getRecord( record );
    }
}
