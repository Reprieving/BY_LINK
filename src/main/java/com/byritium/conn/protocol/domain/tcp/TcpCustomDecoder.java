package com.byritium.conn.protocol.domain.tcp;

import com.byritium.conn.protocol.domain.tcp.dto.TcpCustomMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TcpCustomDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int length = byteBuf.readInt();
        if(byteBuf.readableBytes() >= length){
            byte[] bytes = new byte[length];
            byteBuf.readBytes(bytes);
            TcpCustomMessage messageProtocol = new TcpCustomMessage(length, bytes);
            list.add(messageProtocol);
        }else{
            log.info("消息长度不足");
        }
    }
}
