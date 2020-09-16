package com.springKafka.kafkaApp.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import java.util.Map;import java.util.HashMap;
import org.springframework.kafka.core.KafkaAdmin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

@Configuration
public class KafkaTopicConfig {
    
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    
    @Value(value = "${message.topic.name}")
    private String topicName;
    
    /* With AdminClient in Kafka, we can now create topics programmatically.
     * KafkaAdmin Spring bean, will automatically add topics for all beans of type NewTopic
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }
    
    @Bean
    public NewTopic topic1() {
         return new NewTopic(topicName, 1, (short) 1);
    }
}