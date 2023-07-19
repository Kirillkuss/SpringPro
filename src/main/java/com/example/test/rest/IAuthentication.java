package com.example.test.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.test.entity.User;
import com.example.test.response.BaseResponse;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/auth")
@Tag(name = "JWT",description = "Получение токена:")
@ApiResponses(value = {
    @ApiResponse( responseCode = "200", description = "Authentication success", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = ResponseEntity.class ))) }),
    @ApiResponse( responseCode = "400", description = "Bad Request",            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = ResponseEntity.class ))) }),
    @ApiResponse( responseCode = "500", description = "System malfunction",     content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = ResponseEntity.class ))) })
    })
public interface IAuthentication {

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> login( @RequestBody User user );
    
}
