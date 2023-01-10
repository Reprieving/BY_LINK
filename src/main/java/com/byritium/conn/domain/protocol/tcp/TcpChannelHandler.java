package com.byritium.conn.domain.protocol.tcp;

import com.byritium.conn.domain.protocol.mqtt.BootMqttMsgBack;
import com.byritium.conn.domain.protocol.tcp.dto.TcpCustomMessage;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@ChannelHandler.Sharable
public class TcpChannelHandler extends SimpleChannelInboundHandler<TcpCustomMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TcpCustomMessage tcpCustomMessage) throws Exception {
        String msgString = new String(tcpCustomMessage.getContent(), CharsetUtil.UTF_8);
        System.out.println(msgString);
    }
}
