package java.JavaBase.Java8Test;

import java.util.Arrays;

/**
 * java8函数式编程
 */
public class Test1 {
    public static void main(String[] args) {
        String[] strings = {"xx", "yy", "zz", "xx"};
        Arrays.asList(strings).stream().forEach(x -> System.out.println(x));
        Arrays.asList(strings).stream().forEach(System.out::println);

    }
    /**
     * Java.jvm 如何识别是否要加锁，
     * 如果使用synchronized关键字
     *
     * 字节码锁前会加monitorenter
     * 退出同步块会加monitorexit   通过管程的标志位来识别的   通过内部对象monitor对象（依赖操作系统的互斥锁，从用户态切换到内核态去获取）
     */
}
