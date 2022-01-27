package cn.orzlinux.rpc.codec;

/**
 * 序列化
 * @author hqingLau
 **/
public interface Encoder {
    byte[] encode(Object obj);
}
