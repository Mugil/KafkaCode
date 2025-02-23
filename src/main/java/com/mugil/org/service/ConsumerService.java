package com.mugil.org.service;

import com.mugil.org.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics="MsgTopic", groupId ="msgGrp")
    public void consume(User user){
        LOGGER.info(String.format("Message Received -> %s", user));
    }
}
