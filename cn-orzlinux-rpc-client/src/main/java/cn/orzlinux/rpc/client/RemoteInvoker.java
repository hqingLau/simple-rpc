package cn.orzlinux.rpc.client;

import cn.orzlinux.rpc.Request;
import cn.orzlinux.rpc.Response;
import cn.orzlinux.rpc.ServiceDescriptor;
import cn.orzlinux.rpc.codec.Decoder;
import cn.orzlinux.rpc.codec.Encoder;
import cn.orzlinux.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用远程服务的代理类
 * @author hqingLau
 **/
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;
    private Class clazz;

    RemoteInvoker(Class clazz, Encoder encoder,
                  Decoder decoder,
                  TransportSelector selector) {
        this.decoder = decoder;
        this.encoder = encoder;
        this.selector = selector;
        this.clazz = clazz;
    }

                  @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);

        Response resp = invokeRemote(request);
        if(resp == null || resp.getCode()!=0) {
            throw new IllegalStateException("fail to invoke remote: "+resp);
        }
        return resp.getData();
    }

    private Response invokeRemote(Request request) {
        Response resp = null;
        TransportClient client = null;
        try {
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream recive = client.write(new ByteArrayInputStream(outBytes));

            byte[] inBytes = IOUtils.readFully(recive,recive.available());
            resp = decoder.decode(inBytes,Response.class);
        } catch (IOException e) {
            log.warn(e.getMessage(),e);
            resp = new Response();
            resp.setCode(1);
            resp.setMessage("RpcClient got error: "+
                    e.getClass() +
                    ": "+
                    e.getMessage()
            );
        } finally {
            if(client!=null) {
                selector.release(client);
            }
        }
        return resp;
    }
}
