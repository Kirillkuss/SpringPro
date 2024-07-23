package com.example.test.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.test.entity.User;
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
@Tag( name = "6. USERS", description = "CRUD USERS" )
@RequestMapping( "users" )
@ApiResponses(value = {
    @ApiResponse( responseCode = "200", description = "Success",            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
    @ApiResponse( responseCode = "400", description = "Bad request",        content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
    @ApiResponse( responseCode = "500", description = "System malfunction", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) })
    })
public interface IUser {

    @RequestMapping(method = RequestMethod.POST)
    @Operation( description = "Добавить user", summary = "Добавить user")
    public ResponseEntity<User> addUser( @RequestBody User user);
    
}
