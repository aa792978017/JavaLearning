package java.DesignerPattern.VisitorPattern.extend1;

public interface IVisitor {
    public void visit(CommonEmployee commonEmployee);

    public void visit(Manager manager);
    //统计所有员工工资总和
    public int getTotalSalary();
}
