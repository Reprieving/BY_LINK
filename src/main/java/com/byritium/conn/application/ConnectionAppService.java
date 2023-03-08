package com.byritium.conn.application;

import com.byritium.conn.apis.model.CustomMessageBody;
import com.byritium.conn.infra.SpringUtils;
import com.byritium.conn.infra.peristent.respository.ConnectionRepository;
import com.byritium.conn.infra.peristent.respository.SessionRepository;
import com.byritium.conn.infra.rpc.UserAccountRpc;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class ConnectionAppService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserAccountRpc userAccountRpc;

    public void comm(Channel channel){
        //鉴权
        userAccountRpc.auth();

        //存储连接
        ConnectionRepository.connect(channel);

        //存储会话
        sessionRepository.save();
    }
}
