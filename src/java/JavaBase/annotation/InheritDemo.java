package JavaBase.annotation;

import java.lang.annotation.*;
public class InheritDemo {


    @Inherited  //注解可以继承
    @Target(value = ElementType.TYPE) //注解用于类上,因为只有一个参数,可以省略 “value=”
    @Retention(RetentionPolicy.RUNTIME)
    static @interface Test{

    }
    @Test
    static class Base{

    }

    static class Child extends Base {  //继承注解

    }

    public static void main(String[] args) throws ClassNotFoundException {
        //判断Child目前有无Test.class注解,如果改为Class或者Source,就会返回false,因为运行时没有保留
        System.out.println(Class.forName(Child.class.getName()).isAnnotationPresent(Test.class));

    }

}
