package com.byritium.conn.domain.connection;

import com.byritium.conn.infra.general.constance.CustomerType;
import org.springframework.stereotype.Service;

@Service
public class ConnectionAuthService {
    public void auth(CustomerType customerType,String identify){
        switch (customerType){
            case USER:
                break;

            case DEVICE:
                break;
        }
    }
}
