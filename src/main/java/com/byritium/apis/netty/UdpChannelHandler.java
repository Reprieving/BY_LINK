package com.byritium.apis.netty;

import com.byritium.application.ConnectionAppService;
import com.byritium.application.command.ConnectionCommand;
import com.byritium.application.dto.ConnectionDto;
import com.byritium.types.exception.AccountAuthException;
import com.byritium.utils.JacksonUtils;
import com.byritium.utils.SpringUtils;
import com.byritium.types.constance.ProtocolType;
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
        ConnectionCommand command = new ConnectionCommand(protocolType, ctx.channel(), msg, null);
        try {
            ConnectionDto connectionDto = JacksonUtils.deserialize(command.getMessage().toString(),ConnectionDto.class);
            command.setMessage(connectionDto.getMessage());
            connectionAppService.auth(command);
            connectionAppService.comm(command);
        }catch (AccountAuthException e){
            //收到udp消息后，返回消息
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("auth fail", CharsetUtil.UTF_8),
                    datagramPacket.sender()));
        }

        //收到udp消息后，返回消息
        ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("receice data", CharsetUtil.UTF_8),
                datagramPacket.sender()));
    }
}
