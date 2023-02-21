package com.byritium.conn.apis.netty;

import com.byritium.conn.apis.model.CustomMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TcpCustomDecoder extends ByteToMessageDecoder {
    private NettyMarshallingDecoder nettyMarshallingDecoder;

    public TcpCustomDecoder(){
        this.nettyMarshallingDecoder = MarshallingCodeCFactory.buildMarshallingDecoder();
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

    }
}
