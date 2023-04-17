package com.byritium.conn.application.dto;

import com.byritium.conn.infra.general.constance.CustomerType;
import com.byritium.conn.infra.general.constance.ProtocolType;
import lombok.Data;

@Data
public class ConnectionDto {
    private ProtocolType protocolType;
    private CustomerType customerType;
    private String objectId;
    private String publicKey;

    public ConnectionDto(){

    }

    public ConnectionDto(String[] args) {
        this.customerType = CustomerType.valueOf(args[0]);
        this.objectId = args[1];
        this.publicKey = args[2];
    }
}
