package java.DesignerPattern.CompositePattern;
//抽象构建角色
public abstract class Corp {

    private String name = "";

    private String position = "";

    private int salary = 0;

    public Corp(String name, String position, int salary){
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getInfo(){
        String info = "";
        info = "姓名" + this.name;
        info = info + "\t职位" + this.position;
        info = info + "\t薪水" + this.salary;
        return info;
    }
}
