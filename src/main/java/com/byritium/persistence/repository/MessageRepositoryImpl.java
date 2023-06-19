package com.byritium.persistence.repository;

import com.byritium.domain.message.entity.Message;
import com.byritium.domain.message.repository.MessageRepository;
import com.byritium.persistence.convertor.MessageConvertor;
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
