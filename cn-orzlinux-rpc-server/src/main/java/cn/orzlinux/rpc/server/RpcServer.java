package cn.orzlinux.rpc.server;

import cn.orzlinux.rpc.Request;
import cn.orzlinux.rpc.Response;
import cn.orzlinux.rpc.codec.Decoder;
import cn.orzlinux.rpc.codec.Encoder;
import cn.orzlinux.rpc.common.utils.ReflectUtils;
import cn.orzlinux.rpc.transport.RequestHandler;
import cn.orzlinux.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;
    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream to) {
            Response resp = new Response();

            try {
                byte[] inBytes = IOUtils.readFully(recive,recive.available());
                Request request = decoder.decode(inBytes,Request.class);
                log.info("get request: {}",request);

                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis,request);
                resp.setData(ret);
            } catch (IOException e) {
                log.warn(e.getMessage(),e);
                resp.setCode(1);
                resp.setMessage("RpcServer got error: "+
                        e.getClass().getName()+" "+
                        e.getMessage());
            } finally {
                try {
                    byte[] outBytes = encoder.encode(resp);
                    to.write(outBytes);
                    log.info("rpc send data");
                } catch (IOException e) {
                    log.warn(e.getMessage(),e);
                }
            }
        }
    };

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        this.net = ReflectUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);
        this.encoder = ReflectUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectUtils.newInstance(config.getDecoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public <T> void register(Class<T> interfaceClass, Object bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }
}
