package com.mugil.org.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics="MsgTopic", groupId ="msgGrp")
    public void consume(String msg){
        LOGGER.info(String.format("Message Received -> %s", msg));
    }
}
