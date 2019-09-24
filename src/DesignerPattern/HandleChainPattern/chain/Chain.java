package DesignerPattern.HandleChainPattern.chain;

import java.util.List;

/**
 * 定义链条
 * 把所有要执行的Handler放入里面的List
 * 通过游标顺序遍历.
 */
public class Chain {

    private List<ChainHandler> handlers;

    private int index = 0;

    public Chain(List<ChainHandler> handlers) {
        this.handlers = handlers;
    }

    public void proceed(){
        if(index >= handlers.size()){
            return ;
        }
        handlers.get(index++).execute(this);
    }
}
