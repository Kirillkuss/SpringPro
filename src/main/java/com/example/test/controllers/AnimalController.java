package com.example.test.controllers;

import com.example.test.entity.Animal;
import com.example.test.response.BaseResponse;
import com.example.test.rest.IAnimal;
import com.example.test.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AnimalController implements IAnimal {

    @Autowired
    private AnimalService service;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage( String message ){
        kafkaTemplate.send("topicKlinikSecond", message);
    }

    public BaseResponse getAll() throws Exception{
        sendMessage( " SpringPro > AnimalController > getAll");
        return new BaseResponse( 200, "success", service.getAll());
    }

    public BaseResponse getFindById( Long id )  throws Exception{
        sendMessage( " SpringPro >  AnimalController > getFindById");
        return new BaseResponse( 200, "success", service.getById( id ));  
    }

    public BaseResponse delete( Long id ) throws Exception{
        sendMessage( " SpringPro > AnimalController > delete");
        service.delAnimal( id );
        return BaseResponse.success();
    }

    public BaseResponse addAnimal( Animal animal ) throws Exception{
        sendMessage( "SpringPro >  AnimalController > addAnimal");
        service.addAnimal( animal );
        return  BaseResponse.success();
    }

    public BaseResponse modyAnimal( Animal animal ) throws Exception{
        sendMessage( "SpringPro > AnimalController > modyAnimal");
        service.modyAnimal( animal );
        return BaseResponse.success();
    }

    public BaseResponse getCount() throws Exception{
        sendMessage( "SpringPro > AnimalController > getCount: " + service.getCount() );
        return new BaseResponse( 200, "success", service.getCount());
    }
}
