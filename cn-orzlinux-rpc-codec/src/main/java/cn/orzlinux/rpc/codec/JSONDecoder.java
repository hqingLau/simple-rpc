package cn.orzlinux.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * 基于json的反序列化实现
 * @author hqingLau
 **/
public class JSONDecoder implements Decoder{

    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
