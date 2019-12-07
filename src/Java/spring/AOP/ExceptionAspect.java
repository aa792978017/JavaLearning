package Java.spring.AOP;

import Java.spring.ServiceB;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 异常切面类
 * 参数为需要插入切面的类，即要增强的类
 */
@Aspect(ServiceB.class)
public class ExceptionAspect {
    //增强的类方法执行出现异常的时候执行这个方法
    public static void exception(Object object, Method method,
                                 Object[] args, Throwable e){
        System.out.println("exception when calling: "+ method.getName() + "," + Arrays.toString(args));
    }
}
