package cn.orzlinux.rpc.codec;

/**
 * εεΊεε
 * @author hqingLau
 **/
public interface Decoder {
    <T> T decode(byte[] bytes,Class<T> clazz);
}
