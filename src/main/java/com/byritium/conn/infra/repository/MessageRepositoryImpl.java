package com.byritium.conn.infra.repository;

import com.byritium.conn.domain.message.entity.MessageRoot;
import com.byritium.conn.domain.message.repository.MessageRepository;
import org.springframework.stereotype.Component;

@Component
public class MessageRepositoryImpl implements MessageRepository {
    @Override
    public int save(MessageRoot messageRoot) {
        return 0;
    }
}
