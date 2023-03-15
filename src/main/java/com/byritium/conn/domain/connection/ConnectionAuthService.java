package com.byritium.conn.domain.connection;

import com.byritium.conn.infra.general.constance.CustomerType;
import org.springframework.stereotype.Service;

@Service
public class ConnectionAuthService {
    public void auth(String identify){
        String[] args = identify.split(",");
        CustomerType customerType = CustomerType.valueOf(args[0]);
        String sessionId = args[1];
        switch (customerType){
            case USER:
                break;

            case DEVICE:
                break;
        }
    }
}
