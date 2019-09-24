package DesignerPattern.HandleChainPattern;

public class Women implements IWomen {

    //用来描述妇女状态，未出嫁 1,出嫁 2,夫死 3
    private int type = 0;
    //妇女的请求
    private String request = "";

    //构造函数传递过来请求
    public Women(int type, String request){
        this.type = type;
        switch (this.type){
            case 1:
                this.request = "女儿的请求是" + request;
                break;
            case 2:
                this.request = "妻子的请求是" + request;
                break;
            case 3:
                this.request = "母亲的请求是" + request;
                break;
        }
    }



    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public String getRequest() {
        return this.request;
    }
}
