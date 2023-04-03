package com.byritium.conn.infra.rpc;

import com.byritium.conn.application.dto.ConnectionAuthDto;
import com.byritium.conn.infra.general.constance.CustomerType;
import org.springframework.stereotype.Service;

@Service
public class ConnectionAuthAclService {
    public void auth(ConnectionAuthDto connectionAuthDto){
        CustomerType customerType = connectionAuthDto.getCustomerType();
        String objectId = connectionAuthDto.getObjectId();
        String publicKey = connectionAuthDto.getPublicKey();


        switch (customerType){
            case USER:
                break;

            case DEVICE:
                break;
        }
    }
}
