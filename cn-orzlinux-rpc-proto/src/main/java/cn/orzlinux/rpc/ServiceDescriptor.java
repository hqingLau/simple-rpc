package cn.orzlinux.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 表示服务
 * @author hqingLau
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    //类名
    private  String clazz;
    //方法名
    private String method;
    //返回类型
    private String returnType;
    //参数类型
    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());
        Class[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        int idx=0;
        for(Class pc:parameterClasses) {
            parameterTypes[idx++] = pc.getName();
        }
        sdp.setParameterTypes(parameterTypes);
        return sdp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor) o;
        return clazz.equals(that.clazz) && method.equals(that.method) && returnType.equals(that.returnType) && Arrays.equals(parameterTypes, that.parameterTypes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(clazz, method, returnType);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "clazz='" + clazz + '\'' + ", method='" + method + '\'' + ", returnType='" + returnType + '\'' + ", parameterTypes=" + Arrays.toString(parameterTypes) + '}';
    }
}
