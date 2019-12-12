package java.DesignerPattern.BridgePattern;
//修正抽象化角色
public class HouseCorp extends Corp {
    public HouseCorp(Product product) {
        super(product);
    }

    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("房地产构思赚大钱了...");
    }
}
