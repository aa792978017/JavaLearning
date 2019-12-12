package java.DesignerPattern.MediatorPattern;

public class Purchase extends AbstractColleague {

    public Purchase(AbstractMediator mediator) {
        super(mediator);
    }

    //采购IBM电脑
    public void buyIBMComputer(int number){
        super.mediator.execute("purchase.buy", number);
    }

    //不再采购IBM电脑了
    public void refussBuyIBM(){
        System.out.println("不再采购IBM的电脑了");
    }
}
