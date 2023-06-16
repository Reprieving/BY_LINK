package com.byritium.conn.apis.netty;

import com.byritium.conn.application.ConnectionAppService;
import com.byritium.conn.infra.SpringUtils;
import com.byritium.conn.infra.general.constance.CustomerType;
import com.byritium.conn.infra.general.constance.ProtocolType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@ChannelHandler.Sharable
public class HttpChannelHandler extends SimpleChannelInboundHandler<Object> {
    private static final ProtocolType protocolType = ProtocolType.HTTP;

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
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) {
        Channel channel = channelHandlerContext.channel();
        ConnectionAppService connectionAppService = SpringUtils.getBean(ConnectionAppService.class);
        connectionAppService.comm(protocolType, channel, msg);

        ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer();
        buf.writeCharSequence("sucess", StandardCharsets.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
        response.headers().set("Content-Type", "application/json;charset=UTF-8");
        response.headers().set("Content-Length", response.content().readableBytes());
        channelHandlerContext.channel().writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }


}
