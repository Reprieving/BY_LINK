package com.byritium.conn.infra.repository.convertor;

import com.byritium.conn.domain.message.entity.Message;
import com.byritium.conn.domain.message.entity.po.MessagePo;

public class MessageConvertor {
    public static MessagePo convertPo(Message message){
        MessagePo messagePo = new MessagePo();
        return messagePo;
    }
}
