package cn.orzlinux.rpc.client;

import cn.orzlinux.rpc.codec.Decoder;
import cn.orzlinux.rpc.codec.Encoder;
import cn.orzlinux.rpc.common.utils.ReflectUtils;

import java.lang.reflect.Proxy;

public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectUtils.newInstance(config.getDecoderClass());
        this.selector = ReflectUtils.newInstance(config.getSelectorClass());

        this.selector.init(this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass());
    }

    // 获取接口的代理对象
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz,encoder,decoder,selector)
        );
    }
}
