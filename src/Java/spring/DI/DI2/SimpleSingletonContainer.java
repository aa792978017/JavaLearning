package Java.spring.DI.DI2;

import Java.spring.DI.DI1.SimpleInject;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleSingletonContainer {
    //存储实例化出来的单例,ConcurrentHashMap满足线程安全
    private static Map<Class<?>,Object> instances = new ConcurrentHashMap<>();

    public static <T> T getInstance(Class<T> clazz){

        try{
            //判断是否有单例注解
            boolean singleton = clazz.isAnnotationPresent(SimpleSingleton.class);
            //不是单例，直接创建，不用保存到map里面
            if (!singleton){
                return createInstance(clazz);
            }
            //是单例，加锁同步创建过程，保存到map里面
            Object obj = instances.get(clazz);
            if (obj != null){
                return (T) obj;
            }
            synchronized (clazz){
                obj = instances.get(clazz);
                if (obj == null){
                    obj = createInstance(clazz);
                    instances.put(clazz,obj);
                }
                return (T) obj;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static <T> T createInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        //获取类实例
        T obj = clazz.newInstance();
        //给有注解的成员变量做DI
        //获取成员变量集合
        Field[] fields = clazz.getDeclaredFields();
        //遍历成员变量集合
        for (Field field : fields){
            //是否带有需要注入的注解
            if (field.isAnnotationPresent(SimpleInject.class)){
                //是否为private，是就设置继续强制访问
                if (!field.isAccessible()){
                    field.setAccessible(true);
                }
                //获取成员变量的类型的class
                Class fieldType = field.getType();
                //反射创建实例后设置成员变量
                field.set(obj,fieldType.newInstance());
            }
        }
        //返回创建好的对象
        return obj;
    }
}
