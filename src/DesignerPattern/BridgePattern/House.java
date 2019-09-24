package DesignerPattern.BridgePattern;
//具体实现化角色
public class House extends Product {
    @Override
    public void beProducted() {
        System.out.println("生产出来的房子是这样的...");
    }

    @Override
    public void beSelled() {
        System.out.println("生产出来的房子卖出去了...");
    }
}
