package com.mugil.org.controller;

import com.mugil.org.model.User;
import com.mugil.org.service.UserDetailsProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    private UserDetailsProducerService userDetailsProducerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerController.class);

    public ProducerController(UserDetailsProducerService userDetailsProducerService){
        this.userDetailsProducerService = userDetailsProducerService;
    }

    @PostMapping("/adduser")
    public ResponseEntity<String> placeOrder(@RequestBody User user){
        LOGGER.info("Message from Postman {} ", user);
        userDetailsProducerService.sendMessage(user);
        return ResponseEntity.ok("User Details Received Successfully");
    }


}
