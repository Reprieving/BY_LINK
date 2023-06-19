package com.byritium.conn.infra.repository.persistence;

import com.byritium.message.domain.entity.Message;
import com.byritium.message.domain.repository.MessageRepository;
import com.byritium.conn.infra.repository.convertor.MessageConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageRepositoryImpl implements MessageRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Message message) {
        mongoTemplate.save(MessageConvertor.convertPo(message));
    }
}
