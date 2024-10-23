package com.example.test.config.kafka;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SpingProListener {

    @KafkaListener( topics = "topicKlinikFirst", groupId = "KlinikGroup")
    public void getMessageFirstTopic( String message ){
        log.info( message );
    }

    @KafkaListener( topics = "topicKlinikThird", groupId = "KlinikGroup-AA")
    public void getMessageThirdTopic( String message ){
        log.info( message );
    }
    
}
