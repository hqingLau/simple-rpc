package cn.orzlinux.rpc.server;

import cn.orzlinux.rpc.Request;
import cn.orzlinux.rpc.ServiceDescriptor;
import cn.orzlinux.rpc.common.utils.ReflectUtils;
import cn.orzlinux.rpc.transport.RequestHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理rpc暴露的服务
 * @author hqingLau
 **/
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor,ServiceInstance> services;
    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass,Object bean) {
        Method[] methods = ReflectUtils.getPublicMethods(interfaceClass);
        for(Method method:methods) {
            ServiceInstance sis = new ServiceInstance(bean,method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass,method);
            services.put(sdp,sis);
            log.info("register service:{} {}",sdp.getClazz(),sdp.getMethod());
        }
    }

    public ServiceInstance lookup(Request request) {
        ServiceDescriptor sdp = request.getServiceDescriptor();
        return services.get(sdp);
    }
}
