package com.mugil.org;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Properties;

public class ProducerDemo {
    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class);

    public static void main(String[] args) {

        //Connect to Properties
        Properties properties = new Properties();

        //Connect to Localhost
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        //Set Producer Properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        //Create Producer by passing Properties above
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        //Create Producer Record
        ProducerRecord<String, String> producerRecord =  new ProducerRecord<>("TopicFromJava", "Hello World from Kafka... .!");

        log.info("Message printed");

        //Send Data to Broker
        producer.send(producerRecord);

        //Tell Producer to send all data
       // producer.flush();

       // producer.close();
    }

}
