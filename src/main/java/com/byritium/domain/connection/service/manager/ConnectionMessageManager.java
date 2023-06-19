package com.byritium.domain.connection.service.manager;

import com.byritium.domain.connection.service.ConnectionMessageService;
import com.byritium.constance.ProtocolType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ConnectionMessageManager implements ApplicationContextAware {
    private static final Map<ProtocolType, ConnectionMessageService> map = new HashMap<>();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ConnectionMessageService> beanMap = applicationContext.getBeansOfType(ConnectionMessageService.class);

        for (Map.Entry<String, ConnectionMessageService> entry : beanMap.entrySet()) {
            ConnectionMessageService connectionMessageService = entry.getValue();
            map.put(connectionMessageService.protocolType(), connectionMessageService);
        }
    }

    public ConnectionMessageService get(ProtocolType protocolType){
        return map.get(protocolType);
    }


}
