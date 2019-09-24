package DesignerPattern.HandleChainPattern.ChainOfResponsibilityPattern;

public class Manager extends Approver {
    public Manager(String name) {
        super(name);
    }

    @Override
    public void approve() {
        System.out.println("经理：" +  name + "审核通过");
        if (nexApprover != null)
            this.nexApprover.approve();
    }
}
