package cn.orzlinux.rpc.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类
 * @author hqingLau
 **/
public class ReflectUtils {
    /**
     * 根据class创建对象
     * @param clazz 待创建对象的类
     * @param <T> 对象类型
     * @return 创建好的对象
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取某个class的公有方法
     * @param clazz
     * @return
     */
    public static Method[] getPublicMethods(Class clazz) {
        // 返回当前类所有的方法
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> methodList =new ArrayList<>();
        for(Method m:methods) {
            if(Modifier.isPublic(m.getModifiers())) {
                methodList.add(m);
            }
        }
        // new Method[0]就是起一个模板的作用，指定了返回数组的类型，
        // 0是为了节省空间，因为它只是为了说明返回的类型
        // 数组的大小还是list的size决定的
        return methodList.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     * @param obj 被调用的对象
     * @param method 被调用的方法
     * @param args 方法参数
     * @return 返回结果
     */
    public static Object invoke(Object obj,
                                Method method,
                                Object... args) {
        try {
            return method.invoke(obj,args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
