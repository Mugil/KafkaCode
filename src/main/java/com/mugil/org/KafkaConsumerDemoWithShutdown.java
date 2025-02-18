package com.mugil.org;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerDemoWithShutdown {
    public static final Logger log = LoggerFactory.getLogger(KafkaConsumerDemoWithShutdown.class);


    public static void main(String[] args) {
        String strGrpID = "TopicFromJavaGrp";
        String strTopic = "TopicFromJava";

        //Consumer Properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", strGrpID);
        properties.setProperty("auto.offset.reset", "earliest");

        //Create Consumer with above properties
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        final Thread mainThread = Thread.currentThread();

        //Shutdown hook should be started before calling Subscribe and polling
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                log.info("Detected Shutdown Hook, Lets Exit consumer Wakeup..");

                //Telling to call wakeup incase exit is pressed
                consumer.wakeup();

                try{
                    mainThread.join();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        try {
            consumer.subscribe(Arrays.asList(strTopic));

            //Until the loop runs, keep looking for topics from producer
            while(true) {
                log.info("polling... . . ");

                ConsumerRecords<String, String> arrRecords = consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> record : arrRecords) {
                    log.info("Key:{}, Value:{}, Partition:{}, Offset:{}", record.key(), record.value(), record.partition(), record.offset());
                }
            }
        }catch(WakeupException e){
            //Control comes here incase of exit is called during execution
            log.info("Polling Interuppted.... . . .");
        }catch (Exception e){
            log.error(e.getMessage());
        }finally {
            consumer.close();
            log.info("Consumer is gracefully shutdown...");
        }
    }
}
