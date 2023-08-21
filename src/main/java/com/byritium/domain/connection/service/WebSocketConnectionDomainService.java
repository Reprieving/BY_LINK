package com.byritium.domain.connection.service;

import com.byritium.application.dto.ConnectionDto;
import com.byritium.domain.account.service.AccountAuthService;
import com.byritium.domain.connection.external.AuthExternalService;
import com.byritium.types.constance.ProtocolType;
import com.byritium.types.external.ConnectionAuth;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;

@Service
@Slf4j
public class WebSocketConnectionDomainService implements ConnectionMessageService {
    @Override
    public ProtocolType protocolType() {
        return ProtocolType.WEBSOCKET;
    }

    @Override
    public ConnectionDto auth(Channel channel, Object message, Boolean authFlag, AccountAuthService accountAuthService) {
        FullHttpRequest req = (FullHttpRequest) message;
        if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            DefaultFullHttpResponse res = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
            if (res.status().code() != 200) {
                ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
                res.content().writeBytes(buf);
                buf.release();
            }
            ChannelFuture f = channel.writeAndFlush(res);
            // 如果是非Keep-Alive，关闭连接
            if (!isKeepAlive(req) || res.status().code() != 200) {
                f.addListener(ChannelFutureListener.CLOSE);
            }
            return null;
        }

        HttpHeaders httpHeaders = req.headers();
        String username = httpHeaders.get("username");
        String password = httpHeaders.get("password");
        String identifier = httpHeaders.get("identifier");
        Long appId = Long.valueOf(httpHeaders.get("accountId"));
        accountAuthService.authenticate(appId, identifier);
        return new ConnectionDto(identifier);
    }

    @Override
    public ConnectionDto messaged(Channel channel, Object message) {
        WebSocketFrame frame = (WebSocketFrame) message;
        ConnectionDto connectionDto = new ConnectionDto();
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            channel.write(new PongWebSocketFrame(frame.content().retain()));
            return connectionDto;
        }
        if (!(frame instanceof TextWebSocketFrame)) {
            log.debug("不支持二进制消息");
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }
        //返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        log.debug("服务端收到：" + request);
        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString() + channel.id() + "：" + request);
        connectionDto.setMessage(request);
        return connectionDto;
    }
}

