package com.byritium.persistence.convertor;

import com.byritium.persistence.po.MessagePo;
import com.byritium.domain.message.entity.Message;

public class MessageConvertor {
    public static MessagePo convertPo(Message message){
        MessagePo messagePo = new MessagePo();
        return messagePo;
    }
}
