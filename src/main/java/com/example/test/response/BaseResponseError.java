package com.example.test.response;

import javax.persistence.Column;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseResponseError {

    @Column( name = "code")
    @Schema( name        = "code",
             description = "code",
             example     = "500",
             required    = true )
    private int code;
    @Column( name = "message")
    @Schema( name        = "message",
             description = "admin",
             example     = "Ошибка сервера",
             required    = true )
    private String message;
    
}
