package com.example.test.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class BaseResponse<T> {
    @Schema (description = "Код сообщения", name = "code",  example = "999")
    private int code;
    @Schema (description = "Сообщение", name = "message",  example = "System malfunction")
    private String message ;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseResponse success(){
        return new BaseResponse( 0, "success");
    }

    public static BaseResponse error( int code, Throwable e ){
        return new BaseResponse( 0, null == e.getMessage() ? "System malfunction" : e.getMessage());
    }
}
