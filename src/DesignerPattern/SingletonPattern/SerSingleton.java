package DesignerPattern.SingletonPattern;

import java.io.Serializable;

/**
 * 使序列化和反序列化时都是同一个对象
 */
public class SerSingleton implements Serializable {
    private SerSingleton(){
        System.out.println("Singleton is create");
    }
    private static SerSingleton singleton = new SerSingleton();

    public static SerSingleton getInstance(){
        return singleton;
    }

    /**
     * 保证序列化前后的对象都是同一个
     * @return
     */
    private Object readResolve(){
        return singleton;
    }

}
