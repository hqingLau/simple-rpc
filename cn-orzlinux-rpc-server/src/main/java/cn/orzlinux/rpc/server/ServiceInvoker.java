package cn.orzlinux.rpc.server;

import cn.orzlinux.rpc.Request;
import cn.orzlinux.rpc.common.utils.ReflectUtils;

/**
 * 调用service的实际方法
 * @author hqingLau
 **/
public class ServiceInvoker {
    public Object invoke(ServiceInstance serviceInstance, Request request) {
        return ReflectUtils.invoke(serviceInstance.getTarget(),
                serviceInstance.getMethod(),
                request.getParameters());
    }
}
