# KafkaCode


## Things Newly Implemented

1. ProducerController is RestEndpoint which calls ProducerService
2. ProducerService pushes msg to kafka broker
3. ConsumerService  keeps listening to specific topic and prints the msg once the ProducerService is available in broker  


## View Order placed from URL in consumer console
```xml
kafka-console-consumer.bat --topic MsgTopic --bootstrap-server localhost:9092 --from-beginning
```

![alt text](UML.png)
