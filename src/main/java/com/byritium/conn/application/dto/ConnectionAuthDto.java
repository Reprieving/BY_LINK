package com.byritium.conn.application.dto;

import com.byritium.conn.infra.general.constance.CustomerType;
import lombok.Data;

@Data
public class ConnectionAuthDto {
    private CustomerType customerType;
    private String objectId;
    private String publicKey;

}
