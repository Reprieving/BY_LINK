package com.byritium.domain.connection.service;

import com.byritium.application.dto.ConnectionCommDto;
import com.byritium.application.dto.ConnectionDto;
import com.byritium.domain.connection.external.AuthExternalService;
import com.byritium.types.constance.ProtocolType;
import com.byritium.types.external.ConnectionAuth;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HttpConnectionDomainService implements ConnectionMessageService {
    @Autowired
    private AuthExternalService authExternalService;


    @Override
    public ProtocolType protocolType() {
        return ProtocolType.HTTP;
    }

    @Override
    public void auth(Channel channel, Object msg) {
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        HttpHeaders httpHeaders = fullHttpRequest.headers();
        String username = httpHeaders.get("username");
        String password = httpHeaders.get("password");
        String identifier = httpHeaders.get("identifier");
        ConnectionAuth connectionAuth = new ConnectionAuth(username,password,identifier);
        authExternalService.auth(connectionAuth);
    }

    @Override
    public ConnectionCommDto messaged(Channel channel, Object msg) {
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

        ConnectionCommDto connectionCommDto = new ConnectionCommDto();
        connectionCommDto.setMessage(message);
        return connectionCommDto;
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

