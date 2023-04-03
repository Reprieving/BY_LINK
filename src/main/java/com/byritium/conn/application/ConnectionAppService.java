package com.byritium.conn.application;

import com.byritium.conn.application.dto.ConnectionAuthDto;
import com.byritium.conn.application.dto.PublishDto;
import com.byritium.conn.infra.general.constance.CustomerType;
import com.byritium.conn.infra.rpc.ConnectionAuthAclService;
import com.byritium.conn.infra.peristent.respository.ConnectionRepository;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionAppService {

    @Autowired
    private ConnectionAuthAclService connectionAuthACLService;

    public boolean conn(ConnectionAuthDto connectionAuthDto, Channel channel) {
        CustomerType customerType = connectionAuthDto.getCustomerType();
        String objectId = connectionAuthDto.getObjectId();
        String publicKey = connectionAuthDto.getPublicKey();

        //鉴权
        connectionAuthACLService.auth(connectionAuthDto);

        //存储连接
        ConnectionRepository.connect(channel);

        return true;
    }

    public boolean publish(PublishDto publishDto, Channel channel) {
        String objectID = publishDto.getObjectId();


        return true;
    }
}
