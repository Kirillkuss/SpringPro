package com.example.test.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class BaseResponse<T> {
    @Schema (description = "Код сообщения", name = "code",  example = "200")
    private int code;
    @Schema (description = "Сообщение", name = "message",  example = "success")
    private String message ;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Hidden
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseResponse success(){
        return new BaseResponse( 204, "success");
    }

    public static BaseResponse error( int code, Throwable e ){
        return new BaseResponse( 0, null == e.getMessage() ? "System malfunction" : e.getMessage());
    }
}
