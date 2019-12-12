package java.DesignerPattern.VisitorPattern.extend1;

public class Visitor implements IVisitor{
    //经理工资系数
    private final static int MANAGER_COEFFICIENT = 5;
    //员工工资系数
    private final static int COMMONEMPLOYEE_COEFFICIENT = 2;
    //员工工资总和
    private int commonTotalSalary = 0;
    //经理工资总和
    private int managerTotalSalary = 0;

    //获得所有员工工资总和
    @Override
    public int getTotalSalary() {
        return this.commonTotalSalary + this.managerTotalSalary;
    }
    //计算经理工资总和
    private void calManagerSalary(int salary){
        this.managerTotalSalary = this.managerTotalSalary + salary*MANAGER_COEFFICIENT;
    }
    //计算普通员工工资总和
    private void calCommonSalary(int salary){
        this.commonTotalSalary = this.commonTotalSalary + salary*COMMONEMPLOYEE_COEFFICIENT;
    }


    @Override
    public void visit(CommonEmployee commonEmployee) {
        this.calCommonSalary(commonEmployee.getSalary());
    }

    @Override
    public void visit(Manager manager) {
        this.calManagerSalary(manager.getSalary());
    }


}
