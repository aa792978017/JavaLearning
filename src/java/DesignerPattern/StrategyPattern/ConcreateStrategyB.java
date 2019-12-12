package java.DesignerPattern.StrategyPattern;

/**
 * 定义具体的策略实现类
 */
public class ConcreateStrategyB implements Strategy{


    @Override
    public void doSomething() {
        //具体策略的执行过程
        System.out.println("i am concreateStrategyB, do my thing");
    }
}
