package com.byritium.apis.netty;

import com.byritium.application.ConnectionAppService;
import com.byritium.application.command.ConnectionCommand;
import com.byritium.types.exception.AccountAuthException;
import com.byritium.utils.SpringUtils;
import com.byritium.types.constance.ProtocolType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class WebSocketChannelHandler extends SimpleChannelInboundHandler<Object> {
    private static final ProtocolType protocolType = ProtocolType.WEBSOCKET;
    private WebSocketServerHandshaker handshaker;
    private boolean authFlag = false;

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
        Channel channel = ctx.channel();
        ConnectionAppService connectionAppService = SpringUtils.getBean(ConnectionAppService.class);
        ConnectionCommand command = new ConnectionCommand(protocolType, channel, msg, authFlag);
        try {
            if (msg instanceof FullHttpRequest) {
                FullHttpRequest req = (FullHttpRequest) msg;
                WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8081/websocket", null, false);
                handshaker = wsFactory.newHandshaker(req);
                if (handshaker == null) {
                    WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(channel);
                } else {
                    connectionAppService.auth(command);
                    handshaker.handshake(channel, req);
                    authFlag = true;
                }
            } else if (msg instanceof WebSocketFrame) {
                WebSocketFrame frame = (WebSocketFrame) msg;
                connectionAppService.comm(command);
                // 判断是否关闭链路的指令
                if (frame instanceof CloseWebSocketFrame) {
                    handshaker.close(channel, (CloseWebSocketFrame) frame.retain());
                }
            }
        } catch (AccountAuthException e) {
            log.error(e.getMessage());
            ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer();
            buf.writeCharSequence("auth fail", StandardCharsets.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED, buf);
            response.headers().set("Content-Type", "application/json;charset=UTF-8");
            response.headers().set("Content-Length", response.content().readableBytes());
            channel.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //添加连接
        log.debug("客户端加入连接：" + ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        //断开连接
        log.debug("客户端断开连接：" + ctx.channel());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }


}
