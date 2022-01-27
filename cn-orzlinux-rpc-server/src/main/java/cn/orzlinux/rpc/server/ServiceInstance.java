package cn.orzlinux.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * 具体的server服务
 * @author hqingLau
 **/
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;
}
