package cn.orzlinux.rpc.client;

import cn.orzlinux.rpc.Peer;
import cn.orzlinux.rpc.codec.Decoder;
import cn.orzlinux.rpc.codec.Encoder;
import cn.orzlinux.rpc.codec.JSONDecoder;
import cn.orzlinux.rpc.codec.JSONEncoder;
import cn.orzlinux.rpc.transport.HTTPTransportClient;
import cn.orzlinux.rpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass
            = HTTPTransportClient.class;
    private Class<?extends Encoder> encoderClass = JSONEncoder.class;
    private Class<?extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass =
            RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1",3000)
    );
}
