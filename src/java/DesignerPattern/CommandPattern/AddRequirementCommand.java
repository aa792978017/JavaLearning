package DesignerPattern.CommandPattern;
//具体的命令类
public class AddRequirementCommand extends Command {
    @Override
    public void execute() {
        //找到需求组
        super.requirementGroup.find();
        //添加一个个需求
        super.requirementGroup.add();
        //给出计划
        super.requirementGroup.plan();
    }
}
