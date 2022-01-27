package cn.orzlinux.rpc.client;

import cn.orzlinux.rpc.Peer;
import cn.orzlinux.rpc.common.utils.ReflectUtils;
import cn.orzlinux.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class RandomTransportSelector implements TransportSelector{
    // 已经连接好的client
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        clients = new ArrayList<>();
    }
    /**
     * 初始化selector
     *
     * @param peers 可以连接的server端点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现类class
     */
    @Override
    public void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count,1);

        for(Peer peer:peers) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectUtils.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
            log.info("connect server: {}",peer);
        }
    }

    /**
     * 选择一个transport和server做交互
     *
     * @return 网络client
     */
    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    /**
     * 释放用完的client，就是重新放回到list
     *
     * @param client
     */
    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        for(TransportClient client:clients) {
            client.close();
        }
        clients.clear();
    }
}
