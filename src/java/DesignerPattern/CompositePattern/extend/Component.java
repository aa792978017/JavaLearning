package java.DesignerPattern.CompositePattern.extend;

import java.util.ArrayList;

public abstract class Component {
    //个体和整体都有的
    public void doSomething(){

    }

    //增加一个叶子节点或树枝构件
    public abstract void add(Component component);

    //删除一个叶子或树枝构件
    public abstract void remove(Component component);

    //获得分支下的所有叶子节点和树枝构件
    public abstract ArrayList<Component> getChildren();
}
