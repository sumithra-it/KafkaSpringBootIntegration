package com.springKafka.kafkaApp.consumer.configuration;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import java.util.Map;import java.util.HashMap;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

/*
 * Enable Kafka Listener annotated end points
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${consumer.groupId}")
    private String groupId;
	   
	    public ConsumerFactory<String, String> consumerFactory(String groupId) {
	        Map<String, Object> configProps = new HashMap<>();
	        configProps.put(
	        		ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
	        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
	        configProps.put(
	        		ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        configProps.put(
	        		ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        return new DefaultKafkaConsumerFactory<>(configProps);
	    }
	   
	   
	    public ConcurrentKafkaListenerContainerFactory<String, String> 
	      kafkaListenerContainerFactoryForGrp(String groupId) {
	   
	        ConcurrentKafkaListenerContainerFactory<String, String> factory =
	          new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerFactory(groupId));
	        return factory;
	    }  
	    
	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, String> fooKafkaListenerContainerFactory() {
	        return kafkaListenerContainerFactoryForGrp(groupId);
	    }
	 
}
