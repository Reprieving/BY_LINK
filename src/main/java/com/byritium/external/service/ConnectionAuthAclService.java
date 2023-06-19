package com.byritium.external.service;

import com.byritium.application.dto.ConnectionDto;
import com.byritium.constance.CustomerType;
import org.springframework.stereotype.Service;

@Service
public class ConnectionAuthAclService {

    public void auth(ConnectionDto connectionDto) {
        CustomerType customerType = connectionDto.getCustomerType();
        String objectId = connectionDto.getObjectId();
        String publicKey = connectionDto.getPublicKey();

//        switch (customerType) {
//            case USER:
//                userAccountRpc.auth(objectId, publicKey);
//                break;
//
//            case DEVICE:
//                deviceAccountRpc.auth(objectId, publicKey);
//                break;
//        }
    }
}
