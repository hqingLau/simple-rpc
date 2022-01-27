package cn.orzlinux.rpc.codec;

/**
 * 反序列化
 * @author hqingLau
 **/
public interface Decoder {
    <T> T decode(byte[] bytes,Class<T> clazz);
}
