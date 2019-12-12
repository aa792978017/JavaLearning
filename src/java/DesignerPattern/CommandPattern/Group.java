package java.DesignerPattern.CommandPattern;

//抽象组
public abstract class Group {

    //找到这个小组
    public abstract void find();
    //被要求添加一个功能
    public abstract void add();
    //被要求删除一个功能
    public abstract void delete();
    //被要求修改一个功能
    public abstract void change();
    //被要求给出所有变更计划
    public abstract void plan();

    public void rollBack(){
        System.out.println("撤销命令");
    }
}
