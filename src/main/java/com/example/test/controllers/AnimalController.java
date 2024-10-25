package com.example.test.controllers;

import com.example.test.config.kafka.message.Document;
import com.example.test.entity.Animal;
import com.example.test.response.BaseResponse;
import com.example.test.rest.IAnimal;
import com.example.test.services.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AnimalController implements IAnimal {

   
    private final AnimalService service;
    private final KafkaTemplate<String,Animal> kafkaTemplate;

    private void sendAnimal( Animal animal ){
        kafkaTemplate.send("klinikSecond", animal);
    }

    public BaseResponse getAll() throws Exception{
        service.getAll().stream().forEach( animal -> sendAnimal( animal ));
        return new BaseResponse( 200, "success", service.getAll());
    }

    public BaseResponse getFindById( Long id )  throws Exception{
        sendAnimal( service.getById( id ));
        return new BaseResponse( 200, "success", service.getById( id ));  
    }

    public BaseResponse delete( Long id ) throws Exception{
        service.delAnimal( id );
        return BaseResponse.success();
    }

    public BaseResponse addAnimal( Animal animal ) throws Exception{
        service.addAnimal( animal );
        return  BaseResponse.success();
    }

    public BaseResponse modyAnimal( Animal animal ) throws Exception{
        service.modyAnimal( animal );
        return BaseResponse.success();
    }

    public BaseResponse getCount() throws Exception{
        return new BaseResponse( 200, "success", service.getCount());
    }
}
