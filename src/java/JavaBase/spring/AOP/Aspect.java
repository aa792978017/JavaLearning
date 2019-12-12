package java.JavaBase.spring.AOP;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切面注解，
 * 标识该注解的类都是一个切面
 * 它有一个参数，参参数里面的类，就是我们要增强的类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Aspect {
    //标识代理的类
    Class<?>[] value();
}
