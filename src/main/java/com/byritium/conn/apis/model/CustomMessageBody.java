package com.byritium.conn.apis.model;

import com.byritium.conn.infra.general.constance.CustomerType;
import com.byritium.conn.infra.general.constance.MessagePayloadType;
import lombok.Data;

@Data
public class CustomMessageBody {
    private CustomerType customerType;
    private MessagePayloadType messagePayloadType;
    private String messageTopic;
    private String payload;
}
