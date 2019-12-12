package java.DesignerPattern.VisitorPattern;
//具体元素
public class Manager extends Employee{

    private String performance;

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }
}