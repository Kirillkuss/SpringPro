package com.example.test.controllers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.test.rest.IKey;

@RestController
public class PublicKeyController implements IKey {

    @Override
    public ResponseEntity<StringBuilder> getPublicKey() {
        StringBuilder stringBuilder = new StringBuilder();
        try(InputStream inputStream = ImageController.class.getResourceAsStream( "/app.pub" )){
            try( BufferedReader br = new BufferedReader( new InputStreamReader( inputStream ))){
                String line;
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
            }
        }catch(Exception ex ){
           ex.printStackTrace( System.err );
        }
        return ResponseEntity.ok(  stringBuilder );
    }
    
}
