package cn.orzlinux.rpc.server;

import cn.orzlinux.rpc.Request;
import cn.orzlinux.rpc.ServiceDescriptor;
import cn.orzlinux.rpc.common.utils.ReflectUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {
    ServiceManager sm;
    @Before
    public void init() {
        sm = new ServiceManager();
    }

    @Test
    public void register() {
        TestInterface bean = new TestHello();
        sm.register(TestInterface.class,bean);
    }

    @Test
    public void lookup() {
        register();
        Method[] methods = ReflectUtils.getPublicMethods(TestInterface.class);
        ServiceDescriptor sdp = ServiceDescriptor.from(TestInterface.class, methods[0]);
        Request request = new Request();
        request.setServiceDescriptor(sdp);

        ServiceInstance sis = sm.lookup(request);
        assertNotNull(sis);
    }
}