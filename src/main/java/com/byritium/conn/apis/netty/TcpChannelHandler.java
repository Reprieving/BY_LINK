package com.byritium.conn.apis.netty;

import com.byritium.conn.apis.model.TcpCustomMessage;
import com.byritium.conn.application.ConnectionAppService;
import com.byritium.conn.infra.SpringUtils;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class TcpChannelHandler extends SimpleChannelInboundHandler<TcpCustomMessage> {
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

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TcpCustomMessage tcpCustomMessage) throws Exception {
        String msgString = new String(tcpCustomMessage.getContent(), CharsetUtil.UTF_8);
        System.out.println(msgString);
    }
}
