package com.byritium.application;

import com.byritium.application.command.ConnectionCommand;
import com.byritium.application.dto.ConnectionCommDto;
import com.byritium.domain.connection.messaging.BroadcastMessageProducer;
import com.byritium.domain.connection.service.ConnectionMessageService;
import com.byritium.domain.connection.service.manager.ConnectionMessageManager;
import com.byritium.domain.message.entity.Message;
import com.byritium.domain.message.repository.MessageRepository;
import com.byritium.infra.general.constance.ProtocolType;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionAppService {

    @Autowired
    private ConnectionMessageManager connectionMessageManager;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private BroadcastMessageProducer broadcastMessageProducer;

    public void comm(ConnectionCommand command) {
        ProtocolType protocolType = command.getProtocolType();
        Channel channel = command.getChannel();
        Object message = command.getMessage();
        ConnectionMessageService connectionMessageService = connectionMessageManager.get(protocolType);

        //鉴权
        connectionMessageService.auth(channel, message);

        //解析
        ConnectionCommDto connectionCommDto = connectionMessageService.messaged(channel, message);

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
