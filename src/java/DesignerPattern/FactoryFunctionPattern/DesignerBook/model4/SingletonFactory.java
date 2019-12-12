package java.DesignerPattern.FactoryFunctionPattern.DesignerBook.model4;

import java.lang.reflect.Constructor;

/**
 * 通过反射获取单例对象
 */
public class SingletonFactory {
    private static Singleton singleton;
    static{
        try{
            //获取单例Class对象
            Class c = Class.forName(Singleton.class.getName());
            //获取构造方法
            Constructor constructor = c.getDeclaredConstructor();
            //设置私有无参数构造方法是可以访问的
            constructor.setAccessible(true);
            //反射获取单例实例对象
            singleton = (Singleton) constructor.newInstance();
        }catch (Exception ex){
            System.out.println("生成Singleton单例失败");
        }
    }
    public static Singleton getSingleton(){
        return singleton;
    }
}
