package com.springKafka.kafkaApp.producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class MessageProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;
 
    
	public void sendMessage(String msg) {
		/* send API returns a ListenableFuture object - 
		 *   blocks the sending thread and get the result about the message that was sent. To avoid this,
		 *   handle messages through CallBack
		 */
		
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, msg);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("Sent message [" + msg + "] with offset [" + 
									result.getRecordMetadata().offset() + "]");				
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message " + msg + " error:" + ex.getMessage());
				
			}
		});
	}
	
}
