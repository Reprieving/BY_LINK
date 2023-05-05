package com.byritium.conn.application;

import com.byritium.conn.application.dto.ConnectionDto;
import com.byritium.conn.domain.connection.factory.ConnectionProcessor;
import com.byritium.conn.domain.connection.factory.ConnectionProcessorFactory;
import com.byritium.conn.domain.message.entity.Message;
import com.byritium.conn.domain.message.repository.MessageRepository;
import com.byritium.conn.infra.general.constance.CustomerType;
import com.byritium.conn.infra.general.constance.ProtocolType;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionAppService {

    @Autowired
    private ConnectionProcessorFactory connectionProcessorFactory;

    @Autowired
    private MessageRepository messageRepository;

    public void comm(ConnectionDto connectionDto, Channel channel, Object message) {
        ProtocolType protocolType = connectionDto.getProtocolType();
        CustomerType customerType = connectionDto.getCustomerType();
        String objectId = connectionDto.getObjectId();
        String publicKey = connectionDto.getPublicKey();

        ConnectionProcessor connectionProcessor = connectionProcessorFactory.get(protocolType);

        //鉴权
        connectionProcessor.auth(channel,message);

        //发送消息
        connectionProcessor.messaged(channel, message);

        //存储消息
        Message messageRoot = new Message();
        messageRepository.save(messageRoot);

    }

    public boolean disconnect() {
        return true;
    }
}
