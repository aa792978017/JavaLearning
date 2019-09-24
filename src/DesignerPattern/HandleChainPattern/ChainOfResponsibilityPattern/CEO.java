package DesignerPattern.HandleChainPattern.ChainOfResponsibilityPattern;

public class CEO extends Approver {
    public CEO(String name) {
        super(name);
    }

    @Override
    public void approve() {
        System.out.println("CEO: " + name + "审核通过");
        if (nexApprover != null)
            this.nexApprover.approve();
    }
}
