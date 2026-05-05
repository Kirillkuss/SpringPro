package com.example.test.config.kafka.serializer;
/** 
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import com.example.test.config.kafka.message.SendMessageBroker;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageBrokerDeserializer implements Deserializer<SendMessageBroker> {

    @Override
    public SendMessageBroker deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                log.info("Null received at deserializing");
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue( new String( data, "UTF-8" ), SendMessageBroker.class);
        } catch ( Exception e ) {
            throw new SerializationException( "Error when deserializing byte[] to SendMessageBroker" );
        }
    }
    
}*/
