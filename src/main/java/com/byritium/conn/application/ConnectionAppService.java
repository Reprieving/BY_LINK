package com.byritium.conn.application;

import com.byritium.conn.domain.connection.ConnectionAuthService;
import com.byritium.conn.domain.connection.ConnectionVo;
import com.byritium.conn.infra.general.constance.CustomerType;
import com.byritium.conn.infra.peristent.respository.ConnectionRepository;
import com.byritium.conn.infra.peristent.respository.SessionRepository;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionAppService {

    @Autowired
    private ConnectionAuthService connectionAuthService;

    public void comm(ConnectionVo connectionVo, Channel channel){
        String identifier = connectionVo.getIdentifier();
        CustomerType customerType = connectionVo.getCustomerType();

        //鉴权
        connectionAuthService.auth(customerType,identifier);

        //存储连接
        ConnectionRepository.connect(channel);
    }
}
