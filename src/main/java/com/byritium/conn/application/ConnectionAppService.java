package com.byritium.conn.application;

import com.byritium.conn.application.dto.ConnectionDto;
import com.byritium.conn.application.dto.PublishDto;
import com.byritium.conn.domain.connection.manager.ConnectionManager;
import com.byritium.conn.infra.general.constance.CustomerType;
import com.byritium.conn.infra.api.ConnectionAuthAclService;
import com.byritium.conn.infra.general.constance.ProtocolType;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionAppService {

    @Autowired
    private ConnectionManager connectionManager;

    public void comm(ConnectionDto connectionDto, Channel channel, Object message) {
        ProtocolType protocolType = connectionDto.getProtocolType();
        CustomerType customerType = connectionDto.getCustomerType();
        String objectId = connectionDto.getObjectId();
        String publicKey = connectionDto.getPublicKey();

        //鉴权
        connectionManager.auth(protocolType,channel,message);

        //发送消息
        connectionManager.comm(protocolType, channel, message);

        //存储消息

    }

    public boolean publish(PublishDto publishDto, Channel channel) {
        String objectID = publishDto.getObjectId();


        return true;
    }
}
