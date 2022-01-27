package cn.orzlinux.rpc.common.utils;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ReflectUtilsTest {

    @Test
    public void newInstance() {
        TestClass testClass = ReflectUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
    }

    @Test
    public void getPublicMethods() {
        Method[] methods = ReflectUtils.getPublicMethods(TestClass.class);
        assertEquals(methods[0].getName(),"b");
    }

    @Test
    public void invoke() {
        Method[] methods = ReflectUtils.getPublicMethods(TestClass.class);
        Method b = methods[0];
        assertEquals("b",ReflectUtils.invoke(new TestClass(),b));
    }
}