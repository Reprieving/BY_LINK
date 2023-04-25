package com.byritium.conn.domain.connection.manager;

import com.byritium.conn.application.ConnectionAppService;
import com.byritium.conn.application.dto.ConnectionDto;
import com.byritium.conn.infra.SpringUtils;
import com.byritium.conn.infra.general.constance.ProtocolType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;

@Component
@Slf4j
public class WebSocketConnectionProcessor implements ConnectionProcessor{
    private WebSocketServerHandshaker handshaker;
    @Override
    public ProtocolType protocolType() {
        return ProtocolType.WEBSOCKET;
    }

    @Override
    public void auth(Channel channel, Object message) {

    }

    @Override
    public void messaged(Channel channel, Object message) {
        if (message instanceof FullHttpRequest) {//创建websocket
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
                return;
            }

            HttpHeaders httpHeaders = req.headers();
            String identifier = httpHeaders.get("identifier");
            ConnectionDto connectionDto = new ConnectionDto(identifier.split(","));

        } else if (message instanceof WebSocketFrame) {//websocket通信
            WebSocketFrame frame = (WebSocketFrame) message;
            // 判断是否关闭链路的指令
            if (frame instanceof CloseWebSocketFrame) {
                handshaker.close(channel, (CloseWebSocketFrame) frame.retain());
                return;
            }
            // 判断是否ping消息
            if (frame instanceof PingWebSocketFrame) {
                channel.write(new PongWebSocketFrame(frame.content().retain()));
                return;
            }
            // 本例程仅支持文本消息，不支持二进制消息
            if (!(frame instanceof TextWebSocketFrame)) {
                log.debug("本例程仅支持文本消息，不支持二进制消息");
                throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
            }
            //返回应答消息
            String request = ((TextWebSocketFrame) frame).text();
            log.debug("服务端收到：" + request);
            TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString() + channel.id() + "：" + request);

        }

    }


}
