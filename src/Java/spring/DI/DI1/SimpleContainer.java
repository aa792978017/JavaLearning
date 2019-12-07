package Java.spring.DI.DI1;

import java.lang.reflect.Field;

/**
 * DI工厂
 */
public class SimpleContainer {
    public static <T> T getInstance(Class<T> clazz){
        try{
            //工厂获取所需实例
            T obj = clazz.newInstance();
            //获取需要注入实例的成员变量
            Field[] fields = clazz.getDeclaredFields();
            //循环给所有需要注入的成员变量赋值
            for (Field field : fields){
                //如果标注有注解的成员变量,进行注入
                if (field.isAnnotationPresent(SimpleInject.class)){
                    //如果是私有变量，设置允许获取私有变量
                    if (!field.isAccessible()){
                        field.setAccessible(true);
                    }
                    //获取私有变量的Class对象
                    Class<?> fieldClazz = field.getType();
                    //给我们需要的类注入获取到的成员对象的实例
                    field.set(obj,getInstance(fieldClazz));
                }


            }
            //返回注入好的实例
            return obj;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
