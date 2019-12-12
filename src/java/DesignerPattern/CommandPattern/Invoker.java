package java.DesignerPattern.CommandPattern;

//负责人
public class Invoker {
    //要执行命令
    private Command command;

    //客户发出命令
    public void setCommand(Command command){
        this.command = command;
    }

    //执行客户命令
    public void action(){
        command.execute();
    }
}
