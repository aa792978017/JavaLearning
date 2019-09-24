package DesignerPattern.VisitorPattern.extend1;

import DesignerPattern.VisitorPattern.extend1.IVisitor;

//具体元素
public class CommonEmployee extends Employee {

    private String job;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    //允许访问者访问
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
