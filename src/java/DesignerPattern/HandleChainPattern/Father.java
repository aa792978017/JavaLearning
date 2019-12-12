package java.DesignerPattern.HandleChainPattern;

//具体请求处理者
public class Father extends Handler {
    //父亲值处理女儿的请求
    public Father() {
        super(Handler.FATHER_LEVEL_REQUEST);
    }


    @Override
    protected void response(IWomen women) {
        System.out.println("----女儿向父亲请示----");
        System.out.println(women.getRequest());
        System.out.println("父亲的回答是：同意\n");
    }
}
