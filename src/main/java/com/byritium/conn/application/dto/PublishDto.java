package com.byritium.conn.application.dto;

import com.byritium.conn.infra.general.constance.CustomerType;
import lombok.Getter;

@Getter
public class PublishDto {
    private CustomerType customerType;
    private String objectId;
    private String groupId;
}
