package com.byritium.conn.infra.api;

import com.byritium.conn.application.dto.ConnectionAuthDto;
import com.byritium.conn.infra.general.constance.CustomerType;
import com.byritium.conn.infra.rpc.DeviceAccountRpc;
import com.byritium.conn.infra.rpc.UserAccountRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionAuthAclService {
    @Autowired
    private UserAccountRpc userAccountRpc;

    @Autowired
    private DeviceAccountRpc deviceAccountRpc;

    public void auth(ConnectionAuthDto connectionAuthDto) {
        CustomerType customerType = connectionAuthDto.getCustomerType();
        String objectId = connectionAuthDto.getObjectId();
        String publicKey = connectionAuthDto.getPublicKey();


        switch (customerType) {
            case USER:
                userAccountRpc.auth(objectId, publicKey);
                break;

            case DEVICE:
                deviceAccountRpc.auth(objectId, publicKey);
                break;
        }
    }
}
