package cn.orzlinux.rpc.example;

import cn.orzlinux.rpc.server.RpcServer;

public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(CalcService.class,new CalServiceImpl());
        server.start();
    }
}
