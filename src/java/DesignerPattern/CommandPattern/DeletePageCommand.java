package java.DesignerPattern.CommandPattern;
//具体的命令类
public class DeletePageCommand extends Command {

    //具体要执行的命令
    @Override
    public void execute() {
        //找到美工组
        super.pageGroup.find();
        //删除一个页面
        super.pageGroup.delete();
        //给出计划
        super.pageGroup.plan();
    }
}
