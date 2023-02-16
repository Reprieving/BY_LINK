package com.byritium.conn.apis.netty;

import com.byritium.conn.apis.model.CustomMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TcpCustomEncoder extends MessageToByteEncoder<CustomMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CustomMessage customMessage, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(customMessage.getLength());
        byteBuf.writeBytes(customMessage.getContent());
        byteBuf.writeBytes(new byte[]{'\r', '\n'});
    }
}
