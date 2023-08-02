package com.byritium.messaging.impl;

import com.byritium.domain.connection.messaging.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageProducer implements MessageProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
