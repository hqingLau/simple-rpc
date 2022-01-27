package cn.orzlinux.rpc;

import lombok.Data;

/**
 * RPC的一个请求
 * @author hqingLau
 **/
@Data
public class Request {
    private ServiceDescriptor serviceDescriptor;
    // 参数类型不定
    private Object[] parameters;
}
