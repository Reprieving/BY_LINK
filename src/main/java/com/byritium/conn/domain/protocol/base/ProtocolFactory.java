package com.byritium.conn.domain.protocol.base;

import com.byritium.conn.domain.protocol.http.HttpChannelHandler;
import com.byritium.conn.domain.protocol.mqtt.MqttChannelHandler;
import com.byritium.conn.domain.protocol.tcp.TcpChannelHandler;
import com.byritium.conn.domain.protocol.tcp.TcpCustomDecoder;
import com.byritium.conn.domain.protocol.tcp.TcpCustomEncoder;
import com.byritium.conn.domain.protocol.udp.UdpChannelHandler;
import com.byritium.conn.domain.protocol.websocket.WebSocketChannelHandler;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
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
                        new ProtocolHandler(new LoggingHandler(LogLevel.INFO)),
                        //自定义切割符解决粘包半包问题
                        new ProtocolHandler(new DelimiterBasedFrameDecoder(8192, Unpooled.copiedBuffer("\r\n".getBytes()))),
                        new ProtocolHandler(new TcpCustomEncoder()),
                        new ProtocolHandler(new TcpCustomDecoder()),
                        new ProtocolHandler(new TcpChannelHandler())
                )
        );

        map.put(2000,
                List.of(
                        new ProtocolHandler(new LoggingHandler(LogLevel.INFO)),
                        new ProtocolHandler(new UdpChannelHandler())
                )
        );


        map.put(3000,
                List.of(
                        new ProtocolHandler(new LoggingHandler(LogLevel.INFO)),
                        new ProtocolHandler("http-decoder",new HttpRequestDecoder()),
                        new ProtocolHandler("http-aggregator", new HttpObjectAggregator(1024 * 1024 * 100)),
                        new ProtocolHandler("http-encoder",new HttpResponseEncoder()),
                        new ProtocolHandler("http-chunked", new ChunkedWriteHandler()),
                        new ProtocolHandler(new HttpChannelHandler())
                )
        );

        map.put(4000,
                List.of(
                        new ProtocolHandler(new LoggingHandler(LogLevel.INFO)),
                        new ProtocolHandler("http-codec", new HttpServerCodec()),
                        new ProtocolHandler("aggregator", new HttpObjectAggregator(65536)),
                        new ProtocolHandler("http-chunked", new ChunkedWriteHandler()),
                        new ProtocolHandler(new WebSocketChannelHandler())
                )
        );

        map.put(5000,
                List.of(
                        new ProtocolHandler(new LoggingHandler(LogLevel.INFO)),
                        new ProtocolHandler("encoder", MqttEncoder.INSTANCE),
                        new ProtocolHandler("decoder", new MqttDecoder()),
                        new ProtocolHandler(new MqttChannelHandler())
                )
        );

    }


    public Map<Integer, List<ProtocolHandler>> get() {
        return map;
    }

    public List<ProtocolHandler> get(int port) {
        return map.get(port);
    }
}
