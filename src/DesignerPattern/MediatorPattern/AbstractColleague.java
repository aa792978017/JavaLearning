package DesignerPattern.MediatorPattern;
//抽象同事类
public class AbstractColleague {

    //中介
    protected  AbstractMediator mediator;

    //传入中介类
    public AbstractColleague(AbstractMediator mediator){
        this.mediator = mediator;
    }
}
