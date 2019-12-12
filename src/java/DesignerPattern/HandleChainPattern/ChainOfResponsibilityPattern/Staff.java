package java.DesignerPattern.HandleChainPattern.ChainOfResponsibilityPattern;

public class Staff extends Approver {

    public Staff(String name) {
        super(name);
    }

    @Override
    public void approve() {
        System.out.println("员工："+ name + "以审核");
        if (nexApprover != null)
            this.nexApprover.approve();
    }
}
