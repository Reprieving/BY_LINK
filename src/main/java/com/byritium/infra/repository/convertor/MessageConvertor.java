package com.byritium.infra.repository.convertor;

import com.byritium.infra.repository.po.MessagePo;
import com.byritium.domain.message.entity.Message;

public class MessageConvertor {
    public static MessagePo convertPo(Message message){
        MessagePo messagePo = new MessagePo();
        return messagePo;
    }
}
