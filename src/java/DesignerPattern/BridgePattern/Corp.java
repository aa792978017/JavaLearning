package java.DesignerPattern.BridgePattern;
//抽象化角色
public abstract class Corp {
    private Product product;

    public Corp(Product product){
        this.product = product;
    }
    public void makeMoney(){
        this.product.beProducted();
        this.product.beSelled();
    }
}
