package DesignerPattern.SingletonPattern;

/**
 * 单例模式
 */
public class God {
//    这里防止程序并发修改单例
    private volatile static God god;

//    构造方法不实例化，在有需要获取的时候再实例化
    private God(){}

//    开放接口，给外部对象获取实例
    public static God getInstance(){
        if (god == null){
            synchronized(God.class){
                if (god == null){
                    god = new God(); }
            }
        }
        //已经实例化以后就不用再实例化
        return god;
    }

}
