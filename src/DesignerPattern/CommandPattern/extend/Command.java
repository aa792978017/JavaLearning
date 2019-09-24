package DesignerPattern.CommandPattern.extend;

import DesignerPattern.CommandPattern.Group;

public abstract class Command {
    //定义一个子类的全局共享变量，receiver
    protected Group group;

    //传入receiver
    public Command(Group group){
        this.group = group;
    }

}
