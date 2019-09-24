package DesignerPattern.HandleChainPattern.chain;

/**
 * 抽象责任处理类.
 */
public abstract class ChainHandler {

    //实现的链式处理方法
    public void execute(Chain chain){
        handleProcess();
        chain.proceed();
    }
    //实现责任处理类要实现的方法
    protected abstract void handleProcess();
}
