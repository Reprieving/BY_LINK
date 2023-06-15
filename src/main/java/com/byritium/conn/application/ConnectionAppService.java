package com.byritium.conn.application;

import com.byritium.conn.application.command.ConnectionCommand;
import com.byritium.conn.application.dto.ConnectionCommDto;
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

    public void comm(ProtocolType protocolType,Channel channel,Object message) {
        ConnectionProcessor connectionProcessor = connectionProcessorFactory.get(protocolType);

        //鉴权
        connectionProcessor.auth(channel,message);

        //发送消息
        ConnectionCommDto connectionCommDto = connectionProcessor.messaged(channel, message);

        //存储消息
        Message messageRoot = new Message();
        messageRepository.save(messageRoot);

    }

    public boolean disconnect() {
        return true;
    }
}
