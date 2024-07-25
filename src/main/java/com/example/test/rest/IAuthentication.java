package com.example.test.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.test.request.AuthRequest;
import com.example.test.response.BaseResponse;
import com.example.test.response.BaseResponseError;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/auth")
@Tag(name = "1. JWT",description = "Получение токена:")
@ApiResponses(value = {
    @ApiResponse( responseCode = "200", description = "Authentication success", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
    @ApiResponse( responseCode = "401", description = "Unauthorized",           content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponseError.class ))) }),
    @ApiResponse( responseCode = "500", description = "Internal Server Error",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponseError.class ))) })
    })
public interface IAuthentication {

    @PostMapping(value = "/login")
    public ResponseEntity<BaseResponse> login( @RequestBody AuthRequest authRequest );
    
}
