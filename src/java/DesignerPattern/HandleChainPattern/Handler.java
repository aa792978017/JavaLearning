package java.DesignerPattern.HandleChainPattern;

//抽象处理者
public abstract class Handler {

    public final static int FATHER_LEVEL_REQUEST = 1;

    public final static int HUSBAND_LEVEL_REQUEST = 2;

    public final static int SON_LEVEL_REQUEST = 3;

    //设置处理级别
    private int level = 0;
    //设置下一个责任人是谁
    private Handler nextHandler;
    //设置自己能处理的级别
    public Handler(int level){
        this.level = level;
    }

    //请求，需要处理
    public final void HandleMessage(IWomen woman){
        if (woman.getType() == this.level){
            //是当前责任人可以处理的请求
            this.response(woman);
        }else {
            if (this.nextHandler != null){
                //下一个责任人处理
                this.nextHandler.HandleMessage(woman);
            }else{
                //已经没有后续责任人了
                System.out.println("--已经没人可以请示了");
            }
        }
    }

    //设置下一个责任人
    public void setNextHandler(Handler handler){
        this.nextHandler = handler;
    }
    //回应请示
    protected abstract void response(IWomen women);

}
