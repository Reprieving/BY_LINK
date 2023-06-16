package com.byritium.conn.apis.netty;

import com.byritium.conn.application.ConnectionAppService;
import com.byritium.conn.application.command.ConnectionCommand;
import com.byritium.conn.infra.SpringUtils;
import com.byritium.conn.infra.general.constance.ProtocolType;
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
    private static final ProtocolType protocolType = ProtocolType.UDP;

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
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket datagramPacket) throws Exception {
        String msg = datagramPacket.content().toString(CharsetUtil.UTF_8);
        ConnectionAppService connectionAppService = SpringUtils.getBean(ConnectionAppService.class);
        ConnectionCommand command = new ConnectionCommand(protocolType, ctx.channel(), msg);
        connectionAppService.comm(command);
        //收到udp消息后，返回消息
        ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("receice data", CharsetUtil.UTF_8),
                datagramPacket.sender()));
    }
}
