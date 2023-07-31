package com.byritium.domain.connection.service;

import com.byritium.application.dto.ConnectionDto;
import com.byritium.domain.account.service.AccountAuthService;
import com.byritium.domain.connection.external.AuthExternalService;
import com.byritium.types.constance.ProtocolType;
import com.byritium.types.exception.AccountAuthException;
import com.byritium.types.external.ConnectionAuth;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MqttConnectionService implements ConnectionMessageService {
    @Autowired
    private AuthExternalService authExternalService;

    @Override
    public ProtocolType protocolType() {
        return ProtocolType.MQTT;
    }

    @Override
    public ConnectionDto auth(Channel channel, Object message, Boolean authFlag, AccountAuthService accountAuthService) {
        return null;
    }

    @Override
    public ConnectionDto messaged(Channel channel, Object message) {
        MqttMessage mqttMessage = (MqttMessage) message;
        log.info("info--" + mqttMessage.toString());
        MqttFixedHeader mqttFixedHeader = mqttMessage.fixedHeader();
        ConnectionDto connectionDto = new ConnectionDto();
        try {
            if (mqttFixedHeader.messageType().equals(MqttMessageType.CONNECT)) {
                //	在一个网络连接上，客户端只能发送一次CONNECT报文。服务端必须将客户端发送的第二个CONNECT报文当作协议违规处理并断开客户端的连接
                //	to do 建议connect消息单独处理，用来对客户端进行认证管理等 这里直接返回一个CONNACK消息
                MqttConnectMessage mqttConnectMessage = (MqttConnectMessage) message;
                MqttConnectPayload payload = mqttConnectMessage.payload();
                String username = payload.userName();
                String password = new String(payload.passwordInBytes(), CharsetUtil.UTF_8);
                String clientIdentifier = payload.clientIdentifier();
                ConnectionAuth connectionAuth = new ConnectionAuth(username, password, clientIdentifier);
                authExternalService.auth(connectionAuth);

            }

            switch (mqttFixedHeader.messageType()) {
                case PUBLISH:        //	客户端发布消息
                    //	PUBACK报文是对QoS 1等级的PUBLISH报文的响应
                    String publishData = puback(channel, mqttMessage);
                    connectionDto.setMessage(publishData);
                    break;
                case PUBREL:        //	发布释放
                    //	PUBREL报文是对PUBREC报文的响应
                    //	to do
                    pubcomp(channel, mqttMessage);
                    break;
                case SUBSCRIBE:        //	客户端订阅主题
                    //	客户端向服务端发送SUBSCRIBE报文用于创建一个或多个订阅，每个订阅注册客户端关心的一个或多个主题。
                    //	为了将应用消息转发给与那些订阅匹配的主题，服务端发送PUBLISH报文给客户端。
                    //	SUBSCRIBE报文也（为每个订阅）指定了最大的QoS等级，服务端根据这个发送应用消息给客户端
                    // 	to do
                    suback(channel, mqttMessage);
                    break;
                case UNSUBSCRIBE:    //	客户端取消订阅
                    //	客户端发送UNSUBSCRIBE报文给服务端，用于取消订阅主题
                    //	to do
                    unsuback(channel, mqttMessage);
                    break;
                case PINGREQ:        //	客户端发起心跳
                    //	客户端发送PINGREQ报文给服务端的
                    //	在没有任何其它控制报文从客户端发给服务的时，告知服务端客户端还活着
                    //	请求服务端发送 响应确认它还活着，使用网络以确认网络连接没有断开
                    pingresp(channel, mqttMessage);
                    break;
                case DISCONNECT:    //	客户端主动断开连接
                    //	DISCONNECT报文是客户端发给服务端的最后一个控制报文， 服务端必须验证所有的保留位都被设置为0
                    //	to do
                    break;
                default:
                    break;
            }

        } catch (AccountAuthException e) {
            //	构建返回报文， 固定报头
            MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(MqttMessageType.AUTH, false, MqttQoS.FAILURE, false, 0x02);
            MqttMessage mqttMessageBack = new MqttMessage(mqttFixedHeaderBack);
            log.info("auth failure--" + mqttMessageBack);
            channel.writeAndFlush(mqttMessageBack);
        } finally {
            //
        }
        return connectionDto;
    }


    /**
     * 根据qos发布确认
     *
     * @param channel
     * @param mqttMessage
     */
    private String puback(Channel channel, MqttMessage mqttMessage) {
        MqttPublishMessage mqttPublishMessage = (MqttPublishMessage) mqttMessage;
        MqttFixedHeader mqttFixedHeaderInfo = mqttPublishMessage.fixedHeader();
        MqttQoS qos = mqttFixedHeaderInfo.qosLevel();
        byte[] headBytes = new byte[mqttPublishMessage.payload().readableBytes()];
        mqttPublishMessage.payload().readBytes(headBytes);
        String data = new String(headBytes);

        switch (qos) {
            case AT_MOST_ONCE:        //	至多一次
                break;
            case AT_LEAST_ONCE:        //	至少一次
                //	构建返回报文， 可变报头
                MqttMessageIdVariableHeader mqttMessageIdVariableHeaderBack = MqttMessageIdVariableHeader.from(mqttPublishMessage.variableHeader().packetId());
                //	构建返回报文， 固定报头
                MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(MqttMessageType.PUBACK, mqttFixedHeaderInfo.isDup(), MqttQoS.AT_MOST_ONCE, mqttFixedHeaderInfo.isRetain(), 0x02);
                //	构建PUBACK消息体
                MqttPubAckMessage pubAck = new MqttPubAckMessage(mqttFixedHeaderBack, mqttMessageIdVariableHeaderBack);
                log.info("back--" + pubAck.toString());
                channel.writeAndFlush(pubAck);
                break;
            case EXACTLY_ONCE:        //	刚好一次
                //	构建返回报文， 固定报头
                MqttFixedHeader mqttFixedHeaderBack2 = new MqttFixedHeader(MqttMessageType.PUBREC, false, MqttQoS.AT_LEAST_ONCE, false, 0x02);
                //	构建返回报文， 可变报头
                MqttMessageIdVariableHeader mqttMessageIdVariableHeaderBack2 = MqttMessageIdVariableHeader.from(mqttPublishMessage.variableHeader().packetId());
                MqttMessage mqttMessageBack = new MqttMessage(mqttFixedHeaderBack2, mqttMessageIdVariableHeaderBack2);
                log.info("back--" + mqttMessageBack.toString());
                channel.writeAndFlush(mqttMessageBack);
                break;
            default:
                break;
        }

        return data;
    }

    /**
     * 发布完成 qos2
     *
     * @param channel
     * @param mqttMessage
     */
    private void pubcomp(Channel channel, MqttMessage mqttMessage) {
        MqttMessageIdVariableHeader messageIdVariableHeader = (MqttMessageIdVariableHeader) mqttMessage.variableHeader();
        //	构建返回报文， 固定报头
        MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(MqttMessageType.PUBCOMP, false, MqttQoS.AT_MOST_ONCE, false, 0x02);
        //	构建返回报文， 可变报头
        MqttMessageIdVariableHeader mqttMessageIdVariableHeaderBack = MqttMessageIdVariableHeader.from(messageIdVariableHeader.messageId());
        MqttMessage mqttMessageBack = new MqttMessage(mqttFixedHeaderBack, mqttMessageIdVariableHeaderBack);
        log.info("back--" + mqttMessageBack.toString());
        channel.writeAndFlush(mqttMessageBack);
    }

    /**
     * 订阅确认
     *
     * @param channel
     * @param mqttMessage
     */
    private void suback(Channel channel, MqttMessage mqttMessage) {
        MqttSubscribeMessage mqttSubscribeMessage = (MqttSubscribeMessage) mqttMessage;
        MqttMessageIdVariableHeader messageIdVariableHeader = mqttSubscribeMessage.variableHeader();
        //	构建返回报文， 可变报头
        MqttMessageIdVariableHeader variableHeaderBack = MqttMessageIdVariableHeader.from(messageIdVariableHeader.messageId());
        Set<String> topics = mqttSubscribeMessage.payload().topicSubscriptions().stream().map(MqttTopicSubscription::topicName).collect(Collectors.toSet());
        //log.info(topics.toString());
        List<Integer> grantedQoSLevels = new ArrayList<>(topics.size());
        for (int i = 0; i < topics.size(); i++) {
            grantedQoSLevels.add(mqttSubscribeMessage.payload().topicSubscriptions().get(i).qualityOfService().value());
        }
        //	构建返回报文	有效负载
        MqttSubAckPayload payloadBack = new MqttSubAckPayload(grantedQoSLevels);
        //	构建返回报文	固定报头
        MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(MqttMessageType.SUBACK, false, MqttQoS.AT_MOST_ONCE, false, 2 + topics.size());
        //	构建返回报文	订阅确认
        MqttSubAckMessage subAck = new MqttSubAckMessage(mqttFixedHeaderBack, variableHeaderBack, payloadBack);
        log.info("back--" + subAck.toString());
        channel.writeAndFlush(subAck);
    }

    /**
     * 取消订阅确认
     *
     * @param channel
     * @param mqttMessage
     */
    private void unsuback(Channel channel, MqttMessage mqttMessage) {
        MqttMessageIdVariableHeader messageIdVariableHeader = (MqttMessageIdVariableHeader) mqttMessage.variableHeader();
        //	构建返回报文	可变报头
        MqttMessageIdVariableHeader variableHeaderBack = MqttMessageIdVariableHeader.from(messageIdVariableHeader.messageId());
        //	构建返回报文	固定报头
        MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(MqttMessageType.UNSUBACK, false, MqttQoS.AT_MOST_ONCE, false, 2);
        //	构建返回报文	取消订阅确认
        MqttUnsubAckMessage unSubAck = new MqttUnsubAckMessage(mqttFixedHeaderBack, variableHeaderBack);
        log.info("back--" + unSubAck.toString());
        channel.writeAndFlush(unSubAck);
    }

    /**
     * 心跳响应
     *
     * @param channel
     * @param mqttMessage
     */
    private void pingresp(Channel channel, MqttMessage mqttMessage) {
        //	心跳响应报文	11010000 00000000  固定报文
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.PINGRESP, false, MqttQoS.AT_MOST_ONCE, false, 0);
        MqttMessage mqttMessageBack = new MqttMessage(fixedHeader);
        log.info("back--" + mqttMessageBack.toString());
        channel.writeAndFlush(mqttMessageBack);
    }
}

