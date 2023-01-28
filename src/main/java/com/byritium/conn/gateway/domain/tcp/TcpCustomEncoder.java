package com.byritium.conn.gateway.domain.tcp;

import com.byritium.conn.gateway.domain.tcp.dto.TcpCustomMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TcpCustomEncoder extends MessageToByteEncoder<TcpCustomMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, TcpCustomMessage tcpCustomMessage, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(tcpCustomMessage.getLength());
        byteBuf.writeBytes(tcpCustomMessage.getContent());
        byteBuf.writeBytes(new byte[]{'\r', '\n'});
    }
}