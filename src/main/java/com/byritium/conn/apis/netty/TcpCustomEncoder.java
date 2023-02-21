package com.byritium.conn.apis.netty;

import com.byritium.conn.apis.model.CustomMessage;
import com.byritium.conn.apis.model.CustomMessageBody;
import com.byritium.conn.apis.model.CustomMessageHeader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

import java.util.List;
import java.util.Map;

public class TcpCustomEncoder extends MessageToMessageEncoder<CustomMessage> {
    private NettyMarshallingEncoder nettyMarshallingEncoder;

    public TcpCustomEncoder(){
        this.nettyMarshallingEncoder = MarshallingCodeCFactory.buildMarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CustomMessage customMessage, List<Object> out) throws Exception {
        if (customMessage == null || customMessage.getHeader() == null) {
            throw new Exception("The encode message is nul");
        }

        CustomMessageHeader header = customMessage.getHeader();
        CustomMessage body = customMessage.getBody();

        // 分配缓冲区
        ByteBuf outBuf = Unpooled.buffer();
        // 读取消息头字段, 写入缓冲区
        outBuf.writeInt((int) getBufDefaultVal(header.getCrcCode(), 0));
        outBuf.writeInt((int) getBufDefaultVal(header.getLength(), 0));
        outBuf.writeLong(header.getSessionId() == null ? 0L : header.getSessionId());
        outBuf.writeByte((byte)getBufDefaultVal(header.getType(), -1));
        outBuf.writeByte(header.getPriority() == 0 ? (byte)-1 : header.getPriority());
        // 扩展信息, 依次写入大小和内容
        int size = header.getAttachment() == null ? 0 : header.getAttachment().size();
        outBuf.writeInt(size);

        String key;
        byte[] keyArray;
        Object value;
        if (size > 0) {
            for (Map.Entry<String, Object> entry : header.getAttachment().entrySet()) {
                // key serialize
                key = entry.getKey();
                keyArray = key.getBytes("UTF-8");

                outBuf.writeInt(keyArray.length);
                outBuf.writeBytes(keyArray);

                // value encode
                value = entry.getValue();
                nettyMarshallingEncoder.encode(channelHandlerContext, value, outBuf);
            }
        }



        // body使用MarshallingEncoder进行编码
        if (body != null) {
            nettyMarshallingEncoder.encode(channelHandlerContext, customMessage.getBody(), outBuf);
        } else {
            outBuf.writeInt(0);
        }

        outBuf.setInt(4, outBuf.readableBytes());
        out.add(outBuf);

    }


    private Object getBufDefaultVal(Object content, Object defaultVal) {
        if (content == null) {
            return defaultVal;
        }
        return content;
    }
}
