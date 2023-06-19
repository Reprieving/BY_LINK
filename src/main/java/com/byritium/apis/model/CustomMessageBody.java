package com.byritium.apis.model;

import com.byritium.constance.CustomerType;
import com.byritium.constance.MessagePayloadType;
import lombok.Data;

@Data
public class CustomMessageBody {
    private CustomerType customerType;
    private MessagePayloadType messagePayloadType;
    private String messageTopic;
    private String payload;
}
