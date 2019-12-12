package java.DesignerPattern.CommandPattern;

//需求组
public class RequirementGroup extends Group {
    @Override
    public void find() {
        System.out.println("找到需求组");
    }

    @Override
    public void add() {
        System.out.println("客户要求添加一个新功能");
    }

    @Override
    public void delete() {
        System.out.println("客户要求删除一个功能");
    }

    @Override
    public void change() {
        System.out.println("客户要求修改一个功能");
    }

    @Override
    public void plan() {
        System.out.println("客户要求需要变更计划");
    }
}
