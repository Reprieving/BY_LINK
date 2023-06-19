package com.byritium.message.domain.repository;

import com.byritium.message.domain.entity.Message;

public interface MessageRepository {
    void save(Message message);
}
