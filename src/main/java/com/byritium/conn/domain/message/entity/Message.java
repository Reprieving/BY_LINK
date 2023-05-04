package com.byritium.conn.domain.message.entity;

import com.byritium.conn.domain.message.entity.vo.MessageReceiver;
import com.byritium.conn.domain.message.entity.vo.MessageSender;
import lombok.Getter;

@Getter
public class Message {
    private ConnectionVo connectionVo;
    private MessageSender sender;
    private MessageReceiver receiver;
}
