package com.mugil.org.service;

import com.mugil.org.exception.LocationNotAllowedException;
import com.mugil.org.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    //Retry Thrice, First 2 Times would end up in Retry Table, Last Retry would endup in DLT Table
    @RetryableTopic(attempts = "3", include = {LocationNotAllowedException.class})
    @KafkaListener(topics="userDetails", groupId ="usrGrp")
    public void userConsumer1(User user){
        //User from Location Mumbai would be blocked
        if(user.getUserLocation().equals("Mumbai")){
            throw new LocationNotAllowedException("Invalid Location..!");
        }

        LOGGER.info(String.format("Consumer1 Received Message -> %s", user));
        LOGGER.info("--------------------------------------------------------------------------------------------------------------------------------");
    }

    @DltHandler
    public void listedDeadLetterTopic(User user, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset){
        LOGGER.info(String.format("Unable to process -> %s from topic %s", user, topic));
    }
}
