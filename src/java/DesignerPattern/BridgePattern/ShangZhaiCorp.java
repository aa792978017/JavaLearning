package java.DesignerPattern.BridgePattern;
//修正抽象化角色
public class ShangZhaiCorp extends Corp {
    public ShangZhaiCorp(Product product) {
        super(product);
    }

    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("我赚大钱了...");
    }
}
