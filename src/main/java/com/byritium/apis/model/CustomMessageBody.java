package com.byritium.apis.model;

import com.byritium.types.constance.CustomerType;
import com.byritium.types.constance.MessagePayloadType;
import lombok.Data;

@Data
public class CustomMessageBody {
    private CustomerType customerType;
    private MessagePayloadType messagePayloadType;
    private String messageTopic;
    private String payload;
}
