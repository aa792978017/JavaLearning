package DesignerPattern.SingletonPattern;

/**
 * 内部类单例
 * 优点：延迟加载,不需要同步关键字实现,对多线程良好
 * 缺点：不能传参
 */
public class StaticSingleton {
    /**
     * 私有化构造方法
     * 保证其他类无法主动实例化出该单例
     */
    private StaticSingleton(){}

    /**
     * 定义内部类，
     */
    private static class SingletonHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }

    /**
     * 从内部类里面获取实例，实现延迟加载以及同步
     * 当该类被类加载器加载的时候，内部类会被加载，但不会实例化出里面的静态变量
     * 而且单例是在内部类加载的时候完成的，因此对多线程友好，因为一个类不会被加载多次
     * @return
     */
    public static StaticSingleton getInstance(){
        return SingletonHolder.instance;
    }


}
