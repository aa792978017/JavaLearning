package java.DesignerPattern.BridgePattern;
//具体实现化角色
public class IPod extends Product{
    @Override
    public void beProducted() {
        System.out.println("生产出来的IPod是这样的...");
    }

    @Override
    public void beSelled() {
        System.out.println("生产出来的IPod被卖掉了...");
    }
}
