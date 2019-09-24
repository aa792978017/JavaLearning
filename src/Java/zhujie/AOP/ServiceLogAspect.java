package Java.zhujie.AOP;

import Java.zhujie.ServiceA;
import Java.zhujie.ServiceB;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 日志切面，@Aspect注解参数里面的类就是该切面要增强的类
 * 参数为需要插入切面的类，即被代理的类
 */
@Aspect({ServiceA.class, ServiceB.class})
public class ServiceLogAspect {
    //这里的方法的参数跟cglib的MethodInterceptor方法参数一样
    //after的result是标识方执行结果

    //在增强的类执行方法前，执行这个方法
    public static void before(Object object, Method method, Object[] args){
        System.out.println("entering " +
                method.getDeclaringClass().getSimpleName() +
                "::" + method.getName() + ", args:" + Arrays.toString(args));
    }
    //在增强的类方法执行后，执行这个方法
    public static void after(Object object, Method method, Object[] args,
                             Object result){
        System.out.println("leaving " + method.getDeclaringClass().getSimpleName()
                            + "::" + method.getName() + ", result: " + result);
    }
}
