package com.byritium.conn.infra.repository.convertor;

import com.byritium.message.domain.entity.Message;
import com.byritium.conn.infra.repository.po.MessagePo;

public class MessageConvertor {
    public static MessagePo convertPo(Message message){
        MessagePo messagePo = new MessagePo();
        return messagePo;
    }
}
