package com.example.test.config.kafka;
/** 
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import com.example.test.config.kafka.message.SendMessageBroker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SpingProListener {

    @KafkaListener( topics = "klinikFirst", groupId = "KlinikGroup")
    public void getDocument( SendMessageBroker sendMessageBroker ){
        log.info( sendMessageBroker.toString() );
    }

}*/
