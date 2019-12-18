package DesignerPattern.CommandPattern;
//抽象命令类
public abstract class Command {
    //定义好，子类可以直接使用
    protected RequirementGroup requirementGroup = new RequirementGroup();
    protected PageGroup pageGroup = new PageGroup();
    protected CodeGroup codeGroup = new CodeGroup();
    //执行命令的方法
    public abstract void execute();

}
