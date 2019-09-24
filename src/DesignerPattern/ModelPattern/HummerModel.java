package DesignerPattern.ModelPattern;
/**
 * 抽象模板类
 * 定义算法框架
 */
public abstract class HummerModel {

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    protected abstract void engineBoom();

    final public void run(){
        //发动汽车
        this.start();
        //引擎开始轰鸣
        this.engineBoom();
        //手动控制喇叭
        if (this.isAlarm()){
            this.alarm();
        }
        //到达目的地，停车
        this.stop();
    }

    protected boolean isAlarm(){
        //默认喇叭是会响的
        return true;
    }
    public abstract void setAlarm(boolean isAlarm);
}
