package com.example.test.config.kafka.serializer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import com.example.test.entity.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnimalSerializer implements Serializer<Animal> {

    @Override
    public byte[] serialize(String topic, Animal animal) {
        try {
            if ( animal == null ){
                log.info("Null received at serializing");
                return null;
            }
            //log.info( "Serializing Animal" );
            return new ObjectMapper().writeValueAsBytes( animal );
        } catch ( Exception e ) {
            throw new SerializationException( "Error when serializing Animal to byte[]" );
        }
    }

}