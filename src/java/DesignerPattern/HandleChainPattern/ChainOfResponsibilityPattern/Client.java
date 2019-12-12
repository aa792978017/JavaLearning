package java.DesignerPattern.HandleChainPattern.ChainOfResponsibilityPattern;

public class Client {
    public static void main(String[] args) {
        Staff staff = new Staff("小王");
        Manager manager = new Manager("王总");
        CEO ceo = new CEO("王总裁");
        staff.setNexApprover(manager);
        manager.setNexApprover(ceo);
        staff.approve();
    }
}
