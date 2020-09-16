package com.springKafka.kafkaApp.controller;

import java.util.List;

import com.springKafka.kafkaApp.consumer.MessageConsumer;
import com.springKafka.kafkaApp.producer.MessageProducer;

import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaController {
    private MessageProducer producer;
    private MessageConsumer consumer;
 
    public KafkaController(MessageProducer producer, MessageConsumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }
 
    @GetMapping("/kafka/produce")
    public void produce(@RequestParam String message) {
    	producer.sendMessage(message);
    }
    
   @GetMapping("/kafka/consume")
    public List<String> produce() {
      return consumer.getMessages();
  }   
}
