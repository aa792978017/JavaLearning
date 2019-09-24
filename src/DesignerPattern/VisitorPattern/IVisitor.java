package DesignerPattern.VisitorPattern;
//抽象访问者
public interface IVisitor {

    public void visit(CommonEmployee commonEmployee);


    public void visit(Manager manager);
}
