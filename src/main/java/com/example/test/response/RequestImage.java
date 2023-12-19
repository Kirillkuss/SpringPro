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

    @Schema( name        = "path",
            description = "Путь",
            example     = "D://Project/dev/SpringPro/src/main/resources/static/FALCON.jpg",
            required    = true )
    private String path;
    
}
