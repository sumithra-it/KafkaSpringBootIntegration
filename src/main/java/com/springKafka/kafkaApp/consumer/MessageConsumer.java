package com.springKafka.kafkaApp.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;

public class MessageConsumer {
	
	private final List<String> messagesList = new ArrayList<>();
	

	@KafkaListener(topics = "${message.topic.name}", groupId = "consumerGrp1") //, containerFactory = "fooKafkaListenerContainerFactory")
    public void listenGroupone(String message) {
        System.out.println("Received Messasge in group 'consumerGrp1': " + message);
        
        //to store messages together in a list
        synchronized(messagesList) {
        	messagesList.add(message);
        }
	}
	
	public List<String> getMessages(){
		return messagesList;
	}
}
