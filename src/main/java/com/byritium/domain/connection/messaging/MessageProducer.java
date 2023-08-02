package com.byritium.domain.connection.messaging;

public interface MessageProducer {
    void send(String topic, String message);
}
