package DesignerPattern.VisitorPattern.extend1;




import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        IVisitor visitor = new DesignerPattern.VisitorPattern.extend1.Visitor();
        for (Employee employee : mockEmployee()){
            employee.accept(visitor); //通过新增不同的观察者实现扩展，横向扩展，非常灵活
        }
        System.out.println(visitor.getTotalSalary());

    }

    //生成结构对象
    public static List<Employee> mockEmployee(){
        List<Employee> employees = new ArrayList<>();
        //生成张三这个员工
        CommonEmployee zhangSan = new CommonEmployee();
        zhangSan.setJob("编写Java程序的程序员");
        zhangSan.setName("张三");
        zhangSan.setSalary(1000);
        zhangSan.setSex(Employee.MALE);
        //李四这个员工
        CommonEmployee liSi = new CommonEmployee();
        liSi.setJob("超级美工人员");
        liSi.setName("李斯");
        liSi.setSalary(2000);
        liSi.setSex(Employee.FEMALE);
        //王经理
        Manager wang = new Manager();
        wang.setName("王五");
        wang.setSalary(5000);
        wang.setSex(Employee.MALE);
        wang.setPerformance("业绩完美，无懈可击");
        employees.add(zhangSan);
        employees.add(liSi);
        employees.add(wang);
        return employees;

    }
}
