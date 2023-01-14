package com.byritium.conn.protocol.domain.tcp;

import com.byritium.conn.protocol.domain.tcp.dto.TcpCustomMessage;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class TcpChannelHandler extends SimpleChannelInboundHandler<TcpCustomMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TcpCustomMessage tcpCustomMessage) throws Exception {
        String msgString = new String(tcpCustomMessage.getContent(), CharsetUtil.UTF_8);
        System.out.println(msgString);
    }
}
