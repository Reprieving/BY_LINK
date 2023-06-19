package com.byritium.infra.api;

import com.byritium.application.dto.ConnectionDto;
import com.byritium.infra.general.constance.CustomerType;
import com.byritium.infra.rpc.DeviceAccountRpc;
import com.byritium.infra.rpc.UserAccountRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionAuthAclService {
    @Autowired
    private UserAccountRpc userAccountRpc;

    @Autowired
    private DeviceAccountRpc deviceAccountRpc;

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
