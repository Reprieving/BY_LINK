package com.byritium.domain.message.repository;

import com.byritium.domain.message.entity.Message;

public interface MessageRepository {
    void save(Message message);
}
