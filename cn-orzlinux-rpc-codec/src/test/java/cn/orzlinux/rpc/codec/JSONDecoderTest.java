package cn.orzlinux.rpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONDecoderTest {

    @Test
    public void decode() {
        Encoder encoder = new JSONEncoder();
        TestBean testBean = new TestBean();
        testBean.setName("hqinglau");
        testBean.setAge(24);
        byte[] bytes = encoder.encode(testBean);
        assertNotNull(bytes);

        Decoder decoder = new JSONDecoder();
        TestBean testBean1 = decoder.decode(bytes,TestBean.class);
        assertEquals(testBean.getAge(),testBean1.getAge());
        assertEquals(testBean.getName(),testBean1.getName());
    }
}