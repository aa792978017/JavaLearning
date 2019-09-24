package DesignerPattern.SingletonPattern;

/**
 * 痴汉模式单例
 * 不管三七二十一，先实例化出来再说（类加载完成的时候就完成单例的实例）
 * 优点：采取空间换时间的思路,不管用不用,先实例化出来.
 * 缺点：不用的时候浪费空间
 */
public class CrazySingleton {


    /**
     * 类加载的时候就实例化单例
     */
    private static CrazySingleton singleton = new CrazySingleton();

    /**
     * 向外暴露获取单例的方法
     * @return
     */
    public static CrazySingleton getInstance(){
        return singleton;
    }
}