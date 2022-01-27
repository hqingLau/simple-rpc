package cn.orzlinux.rpc.server;

public class TestHello implements TestInterface{
    @Override
    public void hello() {
        System.out.println("hello");
    }
}
