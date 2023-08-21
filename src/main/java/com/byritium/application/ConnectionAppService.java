package com.byritium.application;

import com.byritium.application.command.ConnectionCommand;
import com.byritium.application.dto.ConnectionDto;
import com.byritium.domain.account.service.AccountAuthService;
import com.byritium.domain.connection.messaging.MessageProducer;
import com.byritium.domain.connection.repository.ConnectionRepository;
import com.byritium.domain.connection.service.ConnectionMessageService;
import com.byritium.domain.connection.service.manager.ConnectionMessageManager;
import com.byritium.domain.group.entity.GroupMember;
import com.byritium.domain.group.repository.GroupRepository;
import com.byritium.domain.message.entity.Message;
import com.byritium.domain.message.repository.MessageRepository;
import com.byritium.types.constance.ProtocolType;
import com.byritium.types.constance.SendType;
import com.byritium.utils.JacksonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
    private GroupRepository groupRepository;

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

    public void commForWard(ConnectionCommand command) {
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
        messageProducer.send(connectionDto.getTopic(), connectionDto.getMessage());
    }

    public boolean disconnect() {
        return true;
    }

    @KafkaListener(topics = "SERVER_SEND_CONNECTION", groupId = "group", containerFactory = "kafkaListenerContainerFactory")
    public void commReverse(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String message = record.value();
        try {
            ConnectionDto connectionDto = JacksonUtils.deserialize(message, ConnectionDto.class);
            List<Channel> channelList = new ArrayList<>();
            String content = connectionDto.getMessage();
            SendType sendType = connectionDto.getSendType();
            Channel channel;
            switch (sendType) {
                case POINT_SEND: {
                    channel = connectionRepository.findChannelByObjId(connectionDto.getIdentifier());
                    if (channel != null) {
                        channelList.add(channel);
                    }
                    break;
                }

                case GROUP_SEND: {
                    List<GroupMember> list = groupRepository.findMemberByGroup(connectionDto.getGroupId());
                    for (GroupMember groupMember : list) {
                        channel = connectionRepository.findChannelByObjId(groupMember.getIdentifier());
                        channelList.add(channel);
                    }
                    break;
                }

                case APPLICATION_SEND: {
                    List<GroupMember> list = groupRepository.findMemberByAccountId(connectionDto.getAppId());
                    for (GroupMember groupMember : list) {
                        channel = connectionRepository.findChannelByObjId(groupMember.getIdentifier());
                        channelList.add(channel);
                    }
                    break;
                }
            }
            for (Channel c : channelList) {
                c.writeAndFlush(content);
            }
            ack.acknowledge();
        } catch (JsonProcessingException e) {
            log.error("kafka message deserialize fail,content:{}", message);
        }
    }
}
