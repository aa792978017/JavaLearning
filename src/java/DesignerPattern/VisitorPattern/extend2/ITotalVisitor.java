package java.DesignerPattern.VisitorPattern.extend2;

import java.DesignerPattern.VisitorPattern.IVisitor;

public interface ITotalVisitor extends IVisitor {
    //统计所有员工的工资总和
    public void totalSalary();
}
