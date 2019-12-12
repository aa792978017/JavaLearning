package java.DesignerPattern.SingletonPattern;

/**
 * 双重检查的单例模式
 * 优点：延迟加载,不用时节约空间,保证线程安全
 * 缺点：volatile使得每次都会取内存里面取值,会导致轻微的性能损耗
 */
public class DCLSingleton {

    //加volatile关键字,防止指令重排后,未初始话完成对象就返回堆引用
    private volatile static DCLSingleton singleton;

    /**
     * 私有化构造方法
     */
    private DCLSingleton(){}

    /**
     * 双重检查
     * 以防获取锁以后别的线程已经完成了单例的实例化,确保实例化过程只执行一次
     * @return
     */
    public static DCLSingleton getInstance(){
        if (singleton == null){
            synchronized(DCLSingleton.class){
                if (singleton == null){
                    singleton = new DCLSingleton();
                }
            }
        }
        return singleton;
    }
}
