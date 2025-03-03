# KafkaCode

## Things Newly Implemented
1. Added Java Based config for Kafka Producer and Consumer instead of application.properties


## Already Exists

1. ProducerController is RestEndpoint which calls ProducerService
2. ProducerService pushes msg to kafka broker
3. ConsumerService  keeps listening to specific topic and prints the msg once the ProducerService is available in broker  
4. 1. Created new Model object - User 
5. The Rest Controller Takes Model object as input in POST Mapping method 
6. MessageBuilder is used to build the Payload from User Model Object 
7. Added Asynchronous Callback by getting return value from send in producer


## Notes

1.Using Java based Configuration for Producer and Consumer Kafka Template gives you more control in terms of security and cleaner code 
  and easier management of multiple producers within an application 



![alt text](UML.png)
