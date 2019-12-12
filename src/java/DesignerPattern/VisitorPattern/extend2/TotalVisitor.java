package java.DesignerPattern.VisitorPattern.extend2;

import java.DesignerPattern.VisitorPattern.CommonEmployee;
import java.DesignerPattern.VisitorPattern.Manager;

public class TotalVisitor implements  ITotalVisitor {

    //经理工资系数
    private final static int MANAGER_COEFFICIENT = 5;
    //员工工资系数
    private final static int COMMONEMPLOYEE_COEFFICIENT = 2;
    //员工工资总和
    private int commonTotalSalary = 0;
    //经理工资总和
    private int managerTotalSalary = 0;

    @Override
    public void totalSalary() {
        System.out.println("本公司的月工作总额是：" + this.commonTotalSalary + this.managerTotalSalary);

    }

    @Override
    public void visit(CommonEmployee commonEmployee) {
        this.commonTotalSalary = this.commonTotalSalary + commonEmployee.getSalary()*COMMONEMPLOYEE_COEFFICIENT;
    }

    @Override
    public void visit(Manager manager) {
        this.managerTotalSalary = this.managerTotalSalary + manager.getSalary()*MANAGER_COEFFICIENT;
    }
}
