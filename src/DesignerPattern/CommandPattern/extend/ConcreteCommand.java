package DesignerPattern.CommandPattern.extend;

import DesignerPattern.CommandPattern.Group;
import DesignerPattern.CommandPattern.PageGroup;

//封闭Receiver方式
public class ConcreteCommand extends Command {

    //设置默认的接受者，recevier
    public ConcreteCommand(){
        super(new PageGroup());
    }

    //传入一个recevier
    public ConcreteCommand(Group group) {
        super(group);
    }
}
