package com.byritium.conn.domain.message.repository;

import com.byritium.conn.domain.message.entity.Message;

public interface MessageRepository {
    void save(Message message);
}
