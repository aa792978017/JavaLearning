package DesignerPattern.SingletonPattern;

/**
 * 懒加载的单例
 * 优点：延迟加载,时间换空间策略,等到实际第一次用的时候,再去完成初始化
 * 缺点：加了锁,在并发高的时候,锁的竞争会很激烈
 *
 */
public class LazySingleton {
    /**
     * 先不实例化，等需要用到的时候进行实例化
     * 延迟加载，
     * 因为一个静态变量的实例化可能会比较久，而且类加载以后不一定马上使用，所以没有必要在类加载的时候就马上实例化出来
     */
    private static LazySingleton singleton;

    /**
     * 通过synchronized来进行同步，防止两个进程同时进入方法进行单例的实例化
     * @return
     */
    public synchronized static LazySingleton getInstance(){
        if (singleton != null){
            singleton = new LazySingleton();
        }
        return singleton;
    }

}
