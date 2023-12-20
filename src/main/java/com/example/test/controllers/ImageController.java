package com.example.test.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.example.test.entity.Image;
import com.example.test.repositories.ImageRepository;
import com.example.test.response.BaseResponse;
import com.example.test.response.RequestImage;
import com.example.test.rest.RestImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.persistence.EntityManager;

import java.io.BufferedReader;
import java.io.File;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class ImageController implements RestImage {

    @Autowired ImageRepository imageRepository;
    @Autowired EntityManager entityManager;
    
    private byte[] res = null;

    @Override
    public byte[] getImage( Long id){
        try{
            entityManager.unwrap( Session.class ).doWork(( Connection conn ) ->{
                try( PreparedStatement ps  = conn.prepareStatement("Select * From images im where im.id = " + id ) ){
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        res = IOUtils.toByteArray( rs.getBinaryStream( 3 )) ;
                    }
                } catch (IOException e) {
                    e.printStackTrace(  System.err );
                }
            });
        }catch( Exception  ex ){
            ex.printStackTrace( System.err );
        }
        if( res == null ) throw new IllegalArgumentException( "Нет такого изображения");
        return res;
        
    }

    //"D://Project/dev/SpringPro/src/main/resources/image/FALCON.jpg"
    @Override
    public ResponseEntity<String> addImage(  RequestImage requestImage ) throws IOException {
        File file = new File( requestImage.getName());
        try(FileInputStream fis = new FileInputStream(file)){
            entityManager.unwrap( Session.class ).doWork(( Connection conn ) ->{
                try( PreparedStatement ps  = conn.prepareStatement("INSERT INTO images VALUES (default, ?, ?)") ){
                    ps.setString(1, file.getName());
                    ps.setBinaryStream(2, fis, (int)file.length() );
                    ps.executeUpdate();
                }
            });
        }
        return ResponseEntity.ok( "Добавлено изображение");
    }

    @Override
    public ResponseEntity<byte[]> getFindbyId( Long id ) throws Exception{
        return ResponseEntity.ok( imageRepository.findById( id )
                              .map( s -> s.getImg() )
                              .orElseThrow( () ->new IllegalArgumentException("Нет изображения с таким ИД")));
    }

    @Override
    public ResponseEntity<BaseResponse> addImageFromRepoz( RequestImage requestImage ) throws Exception{
        try(InputStream put = ImageController.class.getResourceAsStream( "/image/" + requestImage.getName() +".jpg" )){
            if( put == null ) throw new IllegalArgumentException("Файл не найден");    
            imageRepository.save( new Image( null, requestImage.getName(), IOUtils.toByteArray( put )));
        }catch(Exception ex ){
           ex.printStackTrace( System.err );
        }
        return ResponseEntity.ok( new BaseResponse(200, "добавлен"));
    }

}
