package java.DesignerPattern.HandleChainPattern;

//抽象请求者
public interface IWomen {

    //获取个人情况
    public int getType();

    //获得个人请示，要干什么
    public String getRequest();
}
