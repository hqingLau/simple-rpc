package cn.orzlinux.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求的handler
 * @author hqingLau
 **/
public interface RequestHandler {
    void onRequest(InputStream recive, OutputStream to);
}
