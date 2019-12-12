package java.JavaBase.jvm.jvmtest;

import java.util.ArrayList;
import java.util.List;

public class test {
    static class HeapOOm{

    }
    public static void main(String[] args) {
        testOutOfMemoryError();
    }

    /**
     * vmoption args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     * Java堆溢出
     */
    public static void testOutOfMemoryError(){
        List<HeapOOm> list = new ArrayList<>();
        for (;;){
            list.add(new HeapOOm());
        }
    }


}
