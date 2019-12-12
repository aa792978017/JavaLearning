package java.DesignerPattern.VisitorPattern.extend2;

import java.DesignerPattern.VisitorPattern.CommonEmployee;
import java.DesignerPattern.VisitorPattern.Employee;
import java.DesignerPattern.VisitorPattern.Manager;

public class ShowVisitor implements IShowVisitor {
    private String info = "";

    @Override
    public void report() {
        System.out.println(this.info);
    }

    @Override
    public void visit(CommonEmployee commonEmployee) {
        this.info = this.info + this.getBasicInfo(commonEmployee)
                + "工作：" + commonEmployee.getJob() + "\t\n";
    }

    @Override
    public void visit(Manager manager) {
        this.info = this.info + this.getBasicInfo(manager)
                + "业绩：" + manager.getPerformance() + "\t\n";
    }
    //组装基本信息
    private String getBasicInfo(Employee employee){
        String info = "姓名：" + employee.getName() + "\t";
        info = info + "性别：" + employee.getSex() + "\t";
        info = info + "薪水：" + employee.getSalary() + "\t";
        return info;
    }

}
