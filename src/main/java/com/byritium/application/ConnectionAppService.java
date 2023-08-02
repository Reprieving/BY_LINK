package com.byritium.application;

import com.byritium.application.command.ConnectionCommand;
import com.byritium.application.dto.ConnectionDto;
import com.byritium.domain.account.service.AccountAuthService;
import com.byritium.domain.connection.messaging.MessageProducer;
import com.byritium.domain.connection.repository.ConnectionRepository;
import com.byritium.domain.connection.service.ConnectionMessageService;
import com.byritium.domain.connection.service.manager.ConnectionMessageManager;
import com.byritium.domain.message.entity.Message;
import com.byritium.domain.message.repository.MessageRepository;
import com.byritium.types.constance.ProtocolType;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConnectionAppService {

    @Autowired
    private ConnectionMessageManager connectionMessageManager;

    @Autowired
    private AccountAuthService accountAuthService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private MessageProducer messageProducer;


    public void auth(ConnectionCommand command) {
        ProtocolType protocolType = command.getProtocolType();
        Channel channel = command.getChannel();
        Object message = command.getMessage();
        Boolean authFlag = command.getAuthFlag();

        ConnectionMessageService connectionMessageService = connectionMessageManager.get(protocolType);
        String channelId = channel.id().asLongText();
        ChannelId channelIdPo = connectionRepository.findAuthByChannelId(channelId);
        if (channelIdPo == null) {
            ConnectionDto connectionDto = connectionMessageService.auth(channel, message, authFlag, accountAuthService);
            connectionRepository.saveAuth(channelId, channel);

            //存储连接
            connectionRepository.saveConnection(connectionDto.getIdentifier(), channel);
        }

    }

    public void comm(ConnectionCommand command) {
        ProtocolType protocolType = command.getProtocolType();
        Channel channel = command.getChannel();
        Object message = command.getMessage();
        ConnectionMessageService connectionMessageService = connectionMessageManager.get(protocolType);

        //解析
        ConnectionDto connectionDto = connectionMessageService.messaged(channel, message);

        //存储消息
        Message messageAgg = Message.builder()
                .protocolType(protocolType)
                .identifier(connectionDto.getIdentifier())
                .times(System.currentTimeMillis())
                .content(connectionDto.getMessage())
                .build();
        messageRepository.save(messageAgg);



        //存储会话

        //推送
        messageProducer.send();
    }

    public boolean disconnect() {
        return true;
    }

    @KafkaListener(topics = "TOPIC_NAME", groupId = "MyGroup1", containerFactory = "kafkaListenerContainerFactory")
    public void listen(){

    }
}
