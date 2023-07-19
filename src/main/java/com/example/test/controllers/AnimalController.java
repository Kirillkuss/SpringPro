package com.example.test.controllers;

import com.example.test.entity.Animal;
import com.example.test.response.BaseResponse;
import com.example.test.rest.IAnimal;
import com.example.test.services.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public BaseResponse getAll() throws Exception{
        sendMessage( "SpringPro --  method getAllAnimals Success ");
        return new BaseResponse( 200, "success", service.getAll());
    }

    public BaseResponse getFindById( Long id )  throws Exception{
        sendMessage( "SpringPro --  method getFindByIdAnimal Success ");
        return new BaseResponse( 200, "success", service.getById( id ));  
    }

    public BaseResponse delete( Long id ) throws Exception{
        sendMessage( "SpringPro --  method delete Success ");
        service.delAnimal( id );
        return BaseResponse.success();
    }

    public BaseResponse addAnimal( Animal animal ) throws Exception{
        sendMessage( "SpringPro --  method addAnimal Success ");
        service.addAnimal( animal );
        return  BaseResponse.success();
    }

    public BaseResponse modyAnimal( Animal animal ) throws Exception{
        sendMessage( "SpringPro --  method modyAnimal Success ");
        service.modyAnimal( animal );
        return BaseResponse.success();
    }

    public BaseResponse getCount() throws Exception{
        sendMessage( "SpringPro --  method getCount Success  count: " + service.getCount() );
        return new BaseResponse( 200, "success", service.getCount());
    }
}
