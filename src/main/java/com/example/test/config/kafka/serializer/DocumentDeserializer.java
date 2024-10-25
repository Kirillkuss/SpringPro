package com.example.test.config.kafka.serializer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import com.example.test.config.kafka.message.Document;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DocumentDeserializer implements Deserializer<Document> {

    @Override
    public Document deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                log.info("Null received at deserializing");
                return null;
            }
            return new ObjectMapper().readValue( new String( data, "UTF-8" ), Document.class);
        } catch ( Exception e ) {
            throw new SerializationException( "Error when deserializing byte[] to Document" );
        }
    }
    
}
