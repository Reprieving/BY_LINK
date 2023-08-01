package com.byritium.domain.connection.service;

import com.byritium.application.dto.ConnectionDto;
import com.byritium.domain.account.entity.AccountAuth;
import com.byritium.domain.account.service.AccountAuthService;
import com.byritium.types.constance.ProtocolType;
import com.byritium.types.exception.AccountAuthException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class HttpConnectionService implements ConnectionMessageService {

    @Override
    public ProtocolType protocolType() {
        return ProtocolType.HTTP;
    }

    @Override
    public ConnectionDto auth(Channel channel, Object msg, Boolean authFlag, AccountAuthService accountAuthService) {
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        HttpHeaders httpHeaders = fullHttpRequest.headers();
        String username = httpHeaders.get("username");
        String password = httpHeaders.get("password");
        String identifier = httpHeaders.get("identifier");

        try {
            accountAuthService.authenticate(identifier);
        }catch (AccountAuthException e){
            ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer();
            buf.writeCharSequence("auth fail", StandardCharsets.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED, buf);
            response.headers().set("Content-Type", "application/json;charset=UTF-8");
            response.headers().set("Content-Length", response.content().readableBytes());
            channel.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }


        ConnectionDto connectionDto = new ConnectionDto();
        return connectionDto;
    }

    @Override
    public ConnectionDto messaged(Channel channel, Object msg) {
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        HttpHeaders httpHeaders = fullHttpRequest.headers();
        String message = null;
        switch (fullHttpRequest.method().name()) {
            case "GET":
                processGetRequest(fullHttpRequest);
                break;
            case "POST":
                if (httpHeaders.get("Content-Type").contains("x-www-form-urlencoded")) {
                    processPostFormRequest(fullHttpRequest);
                } else if (httpHeaders.get("Content-Type").contains("application/json")) {
                    message = processPostJsonRequest(fullHttpRequest);
                }
                break;
            default:
        }

        ConnectionDto connectionDto = new ConnectionDto();
        connectionDto.setMessage(message);
        return connectionDto;
    }


    /**
     * 处理get请求
     *
     * @param request
     */
    private void processGetRequest(FullHttpRequest request) {
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        Map<String, List<String>> params = decoder.parameters();
        params.forEach((key, value) -> System.out.println(key + " ==> " + value));
    }

    /**
     * 处理post form请求
     *
     * @param request
     */
    private void processPostFormRequest(FullHttpRequest request) {
        HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
        List<InterfaceHttpData> httpDataList = decoder.getBodyHttpDatas();
        httpDataList.forEach(item -> {
            if (item.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                Attribute attribute = (Attribute) item;
                try {
                    System.out.println(attribute.getName() + " ==> " + attribute.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * 处理post json请求
     *
     * @param request
     */
    private String processPostJsonRequest(FullHttpRequest request) {
        ByteBuf content = request.content();
        byte[] bytes = new byte[content.readableBytes()];
        content.readBytes(bytes);
        return new String(bytes);
    }
}

