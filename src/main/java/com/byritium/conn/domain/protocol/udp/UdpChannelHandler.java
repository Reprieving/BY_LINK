package com.byritium.conn.domain.protocol.udp;

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
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        String msgString = datagramPacket.content().toString(CharsetUtil.UTF_8);
        System.out.println(msgString);
        //收到udp消息后，返回消息
        channelHandlerContext.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("receice data", CharsetUtil.UTF_8),
                datagramPacket.sender()));
    }
}
