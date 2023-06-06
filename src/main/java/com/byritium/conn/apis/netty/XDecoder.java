package com.byritium.conn.apis.netty;

import com.byritium.conn.application.dto.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class XDecoder extends ByteToMessageDecoder {
    static final int PACKET_SIZE = 20;

    // 用来临时保留没有处理过的请求报文
    ByteBuf tempMsg = Unpooled.buffer();

    // 用来记录把二进制消息读到哪了
    int length = 0;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        System.out.println();
        System.out.println("MyMessageDecoder decode 被调用");
        //
        System.out.println(in);

        // 首先要读入消息长度，然后才能知道处理后面的哪些字节
        // int 为 4 字节
        if(in.readableBytes() >= 4) {
            if (length == 0){
                // 通过readInt()将4字节的length读入
                length = in.readInt();
            }
            // 根据 length 读入内容
            if (in.readableBytes() < length) {
                System.out.println("当前可读数据不够，继续等待。。");
                return;
            }

            // 构建一个 length（目标消息长度）的缓冲数组
            // 注：也正是这个数组保证了读入的长度
            byte[] content = new byte[length];
            if (in.readableBytes() >= length){
                in.readBytes(content);

                // 构建 MyMessageProtocol对象，传递到下一个handler业务处理
                MessageProtocol messageProtocol = new MessageProtocol();
                messageProtocol.setLen(length);
                messageProtocol.setContent(content);
                out.add(messageProtocol);
            }
            length = 0;
        }
    }
}
