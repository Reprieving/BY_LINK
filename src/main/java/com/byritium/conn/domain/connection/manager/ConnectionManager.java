package com.byritium.conn.domain.connection.manager;

import com.byritium.conn.infra.SpringUtils;
import com.byritium.conn.infra.general.constance.ProtocolType;
import io.netty.channel.Channel;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConnectionManager implements ApplicationContextAware {
    private static final Map<ProtocolType, ConnectionProcessor> map = new HashMap<>();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ConnectionProcessor> beanMap = applicationContext.getBeansOfType(ConnectionProcessor.class);

        for (Map.Entry<String, ConnectionProcessor> entry : beanMap.entrySet()) {
            ConnectionProcessor connectionProcessor = entry.getValue();
            map.put(connectionProcessor.protocolType(), connectionProcessor);
        }
    }

    public void auth(ProtocolType protocolType) {
        map.get(protocolType).auth();
    }

    public void comm(ProtocolType protocolType, Channel channel, Object message) {
        map.get(protocolType).messaged(channel, message);
    }

}
