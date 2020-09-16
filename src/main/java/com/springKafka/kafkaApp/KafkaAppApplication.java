package com.springKafka.kafkaApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.springKafka.kafkaApp.producer.MessageProducer;
import com.springKafka.kafkaApp.consumer.MessageConsumer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaAppApplication {

	@Bean
	MessageProducer messageProducer() {
		return new MessageProducer();
	}
	@Bean
	MessageConsumer messageConsumer() {
		return new MessageConsumer();
	}
	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KafkaAppApplication.class, args);
		
		MessageProducer producer = context.getBean(MessageProducer.class);
		MessageConsumer consumer = context.getBean(MessageConsumer.class);
		
		producer.sendMessage("Don't give up, you are doing great!");
//		
//		final CountDownLatch latchtime = new CountDownLatch(3);
//		consumer.latchtime.await(10, TimeUnit.SECONDS);
		
	}

}
