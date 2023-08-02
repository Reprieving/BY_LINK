package com.byritium.apis.netty;

import com.byritium.application.ConnectionAppService;
import com.byritium.application.command.ConnectionCommand;
import com.byritium.types.exception.AccountAuthException;
import com.byritium.utils.SpringUtils;
import com.byritium.types.constance.ProtocolType;
import io.netty.channel.*;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@ChannelHandler.Sharable
public class MqttChannelHandler extends SimpleChannelInboundHandler<Object> {
    private static final ProtocolType protocolType = ProtocolType.MQTT;
    private boolean authFlag = false;
    /**
     * 客户端与服务端第一次建立连接时执行 在channelActive方法之前执行
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ConnectionAppService connectionAppService = SpringUtils.getBean(ConnectionAppService.class);
        super.channelRegistered(ctx);
    }

    /**
     * 客户端与服务端 断连时执行 channelInactive方法之后执行
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ConnectionAppService connectionAppService = SpringUtils.getBean(ConnectionAppService.class);
        super.channelUnregistered(ctx);
    }

    /**
     * 从客户端收到新的数据时，这个方法会在收到消息时被调用
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception, IOException {
        ConnectionAppService connectionAppService = SpringUtils.getBean(ConnectionAppService.class);
        ConnectionCommand command = new ConnectionCommand(protocolType, ctx.channel(), msg, authFlag);

        try {
            connectionAppService.auth(command);
            connectionAppService.comm(command);
        }catch (AccountAuthException e){
            MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(MqttMessageType.AUTH, false, MqttQoS.FAILURE, false, 0x02);
            MqttMessage mqttMessageBack = new MqttMessage(mqttFixedHeaderBack);
            log.info("auth failure--" + mqttMessageBack);
            ctx.channel().writeAndFlush(mqttMessageBack);
        }

    }


    /**
     * 从客户端收到新的数据、读取完成时调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws IOException {
    }

    /**
     * 当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    /**
     * 客户端与服务端第一次建立连接时执行
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    /**
     * 客户端与服务端 断连时执行
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception, IOException {
        super.channelInactive(ctx);
    }

    /**
     * 服务端 当读超时时 会调用这个方法
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception, IOException {
        super.userEventTriggered(ctx, evt);
        ctx.close();
    }


    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

}
