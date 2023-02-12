package com.byritium.conn.apis.netty;

import com.byritium.conn.application.ConnectionAppService;
import com.byritium.conn.infra.SpringUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ChannelHandler.Sharable
public class UdpChannelHandler extends SimpleChannelInboundHandler<DatagramPacket> {
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
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        String msgString = datagramPacket.content().toString(CharsetUtil.UTF_8);
        System.out.println(msgString);
        //收到udp消息后，返回消息
        channelHandlerContext.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("receice data", CharsetUtil.UTF_8),
                datagramPacket.sender()));
    }
}
