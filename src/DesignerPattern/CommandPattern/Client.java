package DesignerPattern.CommandPattern;

public class Client {
    public static void main(String[] args) {
        //定义接头人
        Invoker invoker = new Invoker();
        System.out.println("--客户要求添加一项新功能--");
        //客户下达的命令
        Command command = new AddRequirementCommand();
        //接头人接收命令
        invoker.setCommand(command);
        //执行命令
        invoker.action();
    }
}
