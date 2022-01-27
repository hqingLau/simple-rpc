package cn.orzlinux.rpc.server;

import cn.orzlinux.rpc.codec.Decoder;
import cn.orzlinux.rpc.codec.Encoder;
import cn.orzlinux.rpc.codec.JSONDecoder;
import cn.orzlinux.rpc.codec.JSONEncoder;
import cn.orzlinux.rpc.transport.HTTPTransportServer;
import cn.orzlinux.rpc.transport.TransportServer;
import lombok.Data;

/**
 * server配置
 * @author hqingLau
 **/
@Data
public class RpcServerConfig {
    //网络模块、序列化模块、监听端口
    private final Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private final Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private final Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private final int port = 3000;
}
