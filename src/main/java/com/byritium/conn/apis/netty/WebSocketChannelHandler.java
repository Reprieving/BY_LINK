package com.byritium.conn.apis.netty;

import com.byritium.conn.application.ConnectionAppService;
import com.byritium.conn.application.dto.ConnectionDto;
import com.byritium.conn.infra.ChannelSupervise;
import com.byritium.conn.infra.SpringUtils;
import com.byritium.conn.infra.general.constance.ProtocolType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;

@Slf4j
public class WebSocketChannelHandler extends SimpleChannelInboundHandler<Object> {
    private static final ProtocolType protocolType = ProtocolType.WEBSOCKET;
    private WebSocketServerHandshaker handshaker;

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
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        ConnectionAppService connectionAppService = SpringUtils.getBean(ConnectionAppService.class);

        ConnectionDto connectionDto = new ConnectionDto();
        connectionDto.setProtocolType(protocolType);
        connectionAppService.comm(connectionDto, ctx.channel(), msg);


        if(msg instanceof FullHttpRequest){
            FullHttpRequest req = (FullHttpRequest) msg;
            WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8081/websocket", null, false);
            handshaker = wsFactory.newHandshaker(req);
            if (handshaker == null) {
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            } else {
                handshaker.handshake(ctx.channel(), req);
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //添加连接
        log.debug("客户端加入连接：" + ctx.channel());
        ChannelSupervise.addChannel(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        //断开连接
        log.debug("客户端断开连接：" + ctx.channel());
        ChannelSupervise.removeChannel(ctx.channel());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }


}
