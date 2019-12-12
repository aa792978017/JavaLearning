package java.DesignerPattern.StrategyPattern;

/**
 * 策略的一个封装容器
 * 上下文
 */
public class Context {
    //封装具体的传入策略
    private Strategy strategy = null;
    //传入具体的策略给容器
    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    //对外开放接口
    public void doAnything(){
        strategy.doSomething();
    }
}
