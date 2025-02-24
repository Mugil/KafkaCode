package com.mugil.org.service;

import com.mugil.org.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics="userDetails", groupId ="usrGrp")
    public void userConsumer1(User user){
        LOGGER.info(String.format("Consumer1 Received Message -> %s", user));
        LOGGER.info("--------------------------------------------------------------------------------------------------------------------------------");
    }

    @KafkaListener(topics="userDetails", groupId ="usrGrp")
    public void userConsumer2(User user){
        LOGGER.info(String.format("Consumer2 Received Message -> %s", user));
        LOGGER.info("--------------------------------------------------------------------------------------------------------------------------------");
    }
}
