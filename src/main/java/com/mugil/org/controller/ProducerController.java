package com.mugil.org.controller;

import com.mugil.org.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    private ProducerService producerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerController.class);

    public ProducerController(ProducerService producerService){
        this.producerService= producerService;
    }

    @GetMapping("/sendmsg")
    public ResponseEntity<String> placeOrder(@RequestParam("msg") String msg){
        LOGGER.info("Message from Producer {} ", msg);
        producerService.sendMessage(msg);
        return ResponseEntity.ok("Message Received Successfully");
    }


}
