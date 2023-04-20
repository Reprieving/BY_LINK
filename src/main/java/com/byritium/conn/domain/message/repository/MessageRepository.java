package com.byritium.conn.domain.message.repository;

import com.byritium.conn.domain.message.entity.MessageRoot;

public interface MessageRepository {
    int save(MessageRoot messageRoot);
}
