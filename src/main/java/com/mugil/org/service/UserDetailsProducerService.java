package com.mugil.org.service;

import com.mugil.org.model.User;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsProducerService.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    public UserDetailsProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user){
        LOGGER.info("Sending UserDetails {}", user.toString());

        Message<User> msg = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, "userDetails")
                .build();

        this.kafkaTemplate.send(msg);
    }
}
