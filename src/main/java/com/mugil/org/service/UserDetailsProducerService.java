package com.mugil.org.service;

import com.mugil.org.model.User;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserDetailsProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsProducerService.class);
    private KafkaTemplate<String, Object> kafkaTemplate;

    public UserDetailsProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user){
        LOGGER.info("Sending UserDetails {}", user.toString());

        Message<User> msg = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, "userDetails")
                .build();

        CompletableFuture<SendResult<String, Object>> future = this.kafkaTemplate.send(msg);

        //Asychronous callback
        future.whenComplete((result, ex) ->{
            if(ex == null)
            {
                LOGGER.info("Sent Message=[{}] with offset - {}, partition - {}", user, result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
            }else{
                LOGGER.info("Unable to Send Message...", ex.getMessage());
            }
        });
    }
}
