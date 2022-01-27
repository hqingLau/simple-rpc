package cn.orzlinux.rpc.transport;

import cn.orzlinux.rpc.Peer;

import java.io.InputStream;

/**
 * 1、创建连接
 * 2、发送数据，并且等待响应
 * 3、关闭连接
 * @author hqingLau
 **/
public interface TransportClient {
    void connect(Peer peer);
    // 写完数据等待响应
    InputStream write(InputStream data);
    void close();
}
