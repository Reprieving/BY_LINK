package com.byritium.conn.domain.protocol.tcp;

import com.byritium.conn.domain.protocol.mqtt.BootMqttMsgBack;
import com.byritium.conn.domain.protocol.tcp.dto.TcpCustomMessage;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@ChannelHandler.Sharable
public class TcpChannelHandler extends SimpleChannelInboundHandler<TcpCustomMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TcpCustomMessage tcpCustomMessage) throws Exception {
        String msgString = new String(tcpCustomMessage.getContent(), CharsetUtil.UTF_8);
        System.out.println(msgString);
    }


//    /**
//     * 	客户端与服务端第一次建立连接时执行 在channelActive方法之前执行
//     */
//    @Override
//    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        super.channelRegistered(ctx);
//    }
//
//    /**
//     * 	客户端与服务端 断连时执行 channelInactive方法之后执行
//     */
//    @Override
//    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        super.channelUnregistered(ctx);
//    }
//
//    /**
//     * 	从客户端收到新的数据时，这个方法会在收到消息时被调用
//     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception, IOException {
//
//    }
//
//    /**
//     * 	从客户端收到新的数据、读取完成时调用
//     */
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws IOException {
//    }
//
//    /**
//     * 	当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时
//     */
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
//        ctx.close();
//    }
//
//    /**
//     * 	客户端与服务端第一次建立连接时执行
//     */
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
//    }
//
//    /**
//     * 	客户端与服务端 断连时执行
//     */
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception, IOException {
//        super.channelInactive(ctx);
//    }
//
//    /**
//     * 	服务端 当读超时时 会调用这个方法
//     */
//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception, IOException {
//        super.userEventTriggered(ctx, evt);
//        ctx.close();
//    }
//
//
//    @Override
//    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
//        super.channelWritabilityChanged(ctx);
//    }
}
