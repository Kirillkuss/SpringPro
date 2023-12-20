package com.example.test.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.test.response.BaseResponse;
import com.example.test.response.RequestImage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag( name = "4. IMAGES", description = "Работа с изображениями" )
@RequestMapping( "images" )
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Успешно",        content = { @Content( array = @ArraySchema(schema = @Schema( ))) }),
        @ApiResponse( responseCode = "400", description = "Плохой запрос ", content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponse.class ))) }),
        @ApiResponse( responseCode = "500", description = "Ошибка сервера", content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation =  BaseResponse.class ))) })
    })
public interface RestImage {

    @GetMapping( value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @Operation( description = "Получение изображения", summary = "Получение изображения")
    public @ResponseBody byte[] getImage( @Parameter( description = "Ид изображения" , example = "1") Long id);

    @PostMapping("/add")
    @Operation( description = "Добавить изображения", summary = "Добавить изображения")
    public ResponseEntity<String> addImage( @RequestBody RequestImage requestImage ) throws Exception;

    @GetMapping(value = "/repoz/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @Operation( description = "Получение изображения через репозиторий", summary = "Получение изображения через репозиторий")
    public @ResponseBody ResponseEntity<byte[]> getFindbyId( @Parameter( description = "Ид изображения" , example = "1") Long id ) throws Exception;

    @PostMapping( value = "/repoz/add")
    @Operation( description = "Добавить изображения", summary = "Добавить изображения")
    public ResponseEntity<BaseResponse> addImageFromRepoz( @RequestBody RequestImage requestImage ) throws Exception; 
 
}
