package com.byritium.conn.application;

import com.byritium.conn.application.command.ConnectionCommand;
import com.byritium.conn.application.dto.ConnectionCommDto;
import com.byritium.conn.domain.messaging.BroadcastMessageProducer;
import com.byritium.conn.domain.factory.ConnectionProcessor;
import com.byritium.conn.domain.factory.ConnectionProcessorFactory;
import com.byritium.message.domain.entity.Message;
import com.byritium.message.domain.repository.MessageRepository;
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

    @Autowired
    private BroadcastMessageProducer broadcastMessageProducer;

    public void comm(ConnectionCommand command) {
        ProtocolType protocolType = command.getProtocolType();
        Channel channel = command.getChannel();
        Object message = command.getMessage();
        ConnectionProcessor connectionProcessor = connectionProcessorFactory.get(protocolType);

        //鉴权
        connectionProcessor.auth(channel,message);

        //解析
        ConnectionCommDto connectionCommDto = connectionProcessor.messaged(channel, message);

        //存储
        Message messageRoot = Message.builder()
                .protocolType(protocolType)
                .identifier(connectionCommDto.getIdentifier())
                .times(System.currentTimeMillis())
                .content(connectionCommDto.getMessage())
                .build();

        messageRepository.save(messageRoot);

        //推送

    }

    public boolean disconnect() {
        return true;
    }
}
