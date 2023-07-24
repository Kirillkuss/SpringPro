package com.example.test.config.kafka.serializer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import com.example.test.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonDeserializer implements Deserializer<Person> {

    private ObjectMapper om = new ObjectMapper();

    @Override
    public Person deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                log.info("Null received at deserializing");
                return null;
            }
            log.info( "Deserializing entity Person...");
            return om.readValue( new String( data, "UTF-8" ), Person.class);
        } catch ( Exception e ) {
            throw new SerializationException( "Error when deserializing byte[] to Person" );
        }
    }
    
}
