package com.example.test.controllers;

import com.example.test.entity.Animal;
import com.example.test.entity.Person;
import com.example.test.response.BaseResponse;
import com.example.test.rest.IAnimal;
import com.example.test.services.AnimalService;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.KafkaConsumer;
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
    private KafkaTemplate<String, String> kafkaTemplate;

       /** @Autowired
    private KafkaTemplate<String, Animal> kafkaTemplate;

    public void sendMessage( Animal animal ){
        kafkaTemplate.send("TopicTwo", animal );
    }*/

    public void sendMessage(String msg) {
        kafkaTemplate.send("TopicTwo", msg);
    }

    @KafkaListener( topics = "TopicOne", groupId = "MyGroupTopics")
    public void getMessageTwo( String msg ){
        log.info( msg );
    }

    public ResponseEntity getAll() throws Exception{
        sendMessage( "SpringPro - getAll");
        return ResponseEntity.status( HttpStatus.OK)
                             .body(  service.getAll() ) ;
    }

    public ResponseEntity getFindById( Long id )  throws Exception{
        sendMessage( "SpringPro - getFindById");
        return ResponseEntity.status( HttpStatus.OK)
                             .body( service.getById( id ));  
    }

    public ResponseEntity delete( Long id ) throws Exception{
        sendMessage( "SpringPro - delete");
        return ResponseEntity.status( HttpStatus.NO_CONTENT )
                             .body( BaseResponse.success() );
    }

    public ResponseEntity addAnimal( Animal animal ) throws Exception{
        sendMessage( "SpringPro - addAnimal");
        service.addAnimal( animal );
        return ResponseEntity.status( HttpStatus.NO_CONTENT)
                             .body( BaseResponse.success() ) ;
    }

    public ResponseEntity modyAnimal( Animal animal ) throws Exception{
        sendMessage( animal.toString() );
        service.modyAnimal( animal );
        return ResponseEntity.status( HttpStatus.NO_CONTENT )
                             .body(BaseResponse.success());
    }

    public ResponseEntity getCount() throws Exception{
        //sendMessage( "SpringPro --  method getCount Success  count: " + service.getCount() );
        return ResponseEntity.status( HttpStatus.OK )
                             .body( new BaseResponse<>( 200, "success", service.getCount() )) ;
    }
}
