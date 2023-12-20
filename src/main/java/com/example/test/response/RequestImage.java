package com.example.test.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
/**
 * Входной параметр для добавления изображения
 */
@Getter
@Setter
public class RequestImage {

    @Schema( name        = "name",
            description = "Наименование файла",
            example     = "FALCON",
            required    = true )
    private String name;
    
}
