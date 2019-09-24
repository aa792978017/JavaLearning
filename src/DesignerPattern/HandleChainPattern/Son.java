package DesignerPattern.HandleChainPattern;

public class Son extends Handler{
    //只处理母亲的请求
    public Son() {
        super(Handler.SON_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("----母亲向儿子请示----");
        System.out.println(women.getRequest());
        System.out.println("儿子的回答是：同意\n");
    }
}
