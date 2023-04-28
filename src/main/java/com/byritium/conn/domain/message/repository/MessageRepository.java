package com.byritium.conn.domain.message.repository;

import com.byritium.conn.domain.message.entity.Message;

public interface MessageRepository {
    int save(Message message);
}
