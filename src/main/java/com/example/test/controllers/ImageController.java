package com.example.test.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.test.response.RequestImage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import java.io.File;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
/**
 *  InputStream stream = new FileInputStream(new File("D://Project/dev/SpringPro/src/main/resources/static/FALCON.jpg"));
 *   InputStream in = getClass().getResourceAsStream("/com/example/test/FALCON.jpg");
 */
@Tag( name = "images", description = "Работа с изображениями" )
@RequestMapping( "images" )
@RestController
public class ImageController {
    
    @Autowired EntityManager entityManager;
    
    String message = null;
    InputStream response = null;

    @GetMapping( value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @Operation( description = "Получение изображения", summary = "Получение изображения")
    public @ResponseBody byte[] getImage( @Parameter( description = "Ид" , example = "1") Long id) throws IOException  {
        try{
            entityManager.unwrap( Session.class ).doWork(( Connection conn ) ->{
                try( PreparedStatement ps  = conn.prepareStatement("Select * From images im where im.id = " + id ) ){
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        message = rs.getString( 2 );
                        response = rs.getBinaryStream( 3 );  
                    }
                }
            });
        }catch( Exception ex ){
            
        }
        return IOUtils.toByteArray(response);
    }

    @PostMapping("/add")
    @Operation( description = "Добавить изображения", summary = "Добавить изображения")
    public ResponseEntity<String> addImage( @RequestBody RequestImage requestImage ) throws IOException {
        File file = new File( requestImage.getPath() );
        FileInputStream fis = new FileInputStream(file);
        entityManager.unwrap( Session.class ).doWork(( Connection conn ) ->{
            try( PreparedStatement ps  = conn.prepareStatement("INSERT INTO images VALUES (default, ?, ?)") ){
              //  ps.setLong(1 , 1L );
                ps.setString(1, file.getName());
                ps.setBinaryStream(2, fis, (int)file.length() );
                ps.executeUpdate();
            }
        });
        fis.close();
        return ResponseEntity.ok( "Добавлено изображение");
    }
    
    
    
}
