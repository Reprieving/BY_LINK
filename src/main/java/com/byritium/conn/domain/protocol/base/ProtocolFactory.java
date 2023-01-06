package com.byritium.conn.domain.protocol.base;

import com.byritium.conn.domain.protocol.http.HttpChannelHandler;
import com.byritium.conn.domain.protocol.mqtt.MqttChannelHandler;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Component
public class ProtocolFactory {
    private static Map<Integer, List<ProtocolHandler>> map;

    @PostConstruct
    public void init() {
        map = new Hashtable<>(10);
        map.put(1000,
                List.of(
                        new ProtocolHandler(new HttpServerCodec()),
                        new ProtocolHandler("httpAggregator", new HttpObjectAggregator(1024 * 1024 * 100)),
                        new ProtocolHandler(new HttpChannelHandler())
                )
        );

        map.put(2000,
                List.of(
                        new ProtocolHandler("encoder", MqttEncoder.INSTANCE),
                        new ProtocolHandler("decoder", new MqttDecoder()),
                        new ProtocolHandler(new MqttChannelHandler())
                )
        );

        map.put(3000,
                List.of(
                        new ProtocolHandler("encoder", MqttEncoder.INSTANCE),
                        new ProtocolHandler("decoder", new MqttDecoder()),
                        new ProtocolHandler(new MqttChannelHandler())
                )
        );



    }


    public Map<Integer, List<ProtocolHandler>> get() {
        return map;
    }
}
