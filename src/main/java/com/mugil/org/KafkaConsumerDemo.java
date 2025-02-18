package com.mugil.org;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerDemo {
    public static final Logger log = LoggerFactory.getLogger(KafkaConsumerDemo.class);

    public static void main(String[] args) {
        String strTopic = "TopicFromJava";
        String strGrpId = "TopicFromJavaGrp";

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());

        properties.setProperty("group.id", strGrpId);
        properties.setProperty("auto.offset.reset", "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(strTopic));

        while(true){
            log.info("polling... . . ");

            ConsumerRecords<String, String> arrRecords = consumer.poll(Duration.ofMillis(1000));

            for(ConsumerRecord<String, String> record: arrRecords){
                log.info("Key : " + record.key() + ", Value: " + record.value() + " Partition: "+ record.partition() + ", Offset:" + record.offset());
            }
        }
    }
}
