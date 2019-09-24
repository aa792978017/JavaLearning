package DesignerPattern.HandleChainPattern.ChainOfResponsibilityPattern;

public abstract class Approver {
    protected String name;
    protected Approver nexApprover;

    public Approver(String name) {
        this.name = name;
    }

    public void setNexApprover(Approver nexApprover) {
        this.nexApprover = nexApprover;
    }
    public abstract void approve();
}
