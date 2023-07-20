package com.example.test.controllers;

import com.example.test.entity.Animal;
import com.example.test.response.BaseResponse;
import com.example.test.rest.IAnimal;
import com.example.test.services.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AnimalController implements IAnimal {

    @Autowired
    private AnimalService service;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage( String message ){
        kafkaTemplate.send("TopicTwo", message);
    }

    @KafkaListener( topics = "TopicOne", groupId = "MyGroupTopics")
    public void getMessageTwo( String message ){
        log.info( message );
    }

    public ResponseEntity getAll() throws Exception{
        sendMessage( "SpringPro --  method getAllAnimals Success ");
        return ResponseEntity.status( HttpStatus.OK)
                             .body(  service.getAll() ) ;
    }

    public ResponseEntity getFindById( Long id )  throws Exception{
        sendMessage( "SpringPro --  method getFindByIdAnimal Success ");
        return ResponseEntity.status( HttpStatus.OK)
                             .body( service.getById( id ));  
    }

    public ResponseEntity delete( Long id ) throws Exception{
        sendMessage( "SpringPro --  method delete Success ");
        service.delAnimal( id );
        return ResponseEntity.status( HttpStatus.NO_CONTENT )
                             .body( BaseResponse.success() );
    }

    public ResponseEntity addAnimal( Animal animal ) throws Exception{
        sendMessage( "SpringPro --  method addAnimal Success ");
        service.addAnimal( animal );
        return ResponseEntity.status( HttpStatus.NO_CONTENT)
                             .body( BaseResponse.success() ) ;
    }

    public ResponseEntity modyAnimal( Animal animal ) throws Exception{
        sendMessage( "SpringPro --  method modyAnimal Success ");
        service.modyAnimal( animal );
        return ResponseEntity.status( HttpStatus.NO_CONTENT )
                             .body(BaseResponse.success());
    }

    public ResponseEntity getCount() throws Exception{
        sendMessage( "SpringPro --  method getCount Success  count: " + service.getCount() );
        return ResponseEntity.status( HttpStatus.OK )
                             .body( new BaseResponse<>( 200, "success", service.getCount() )) ;
    }
}
