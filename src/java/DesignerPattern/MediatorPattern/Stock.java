package java.DesignerPattern.MediatorPattern;

public class Stock extends AbstractColleague{

    public Stock(AbstractMediator mediator) {
        super(mediator);
    }

    //刚开始有100台电脑
    public static int COMPUTER_NUMBER = 100;

    //库存增加
    public void increase(int number){
        COMPUTER_NUMBER += number;
    }

    //库存降低
    public void decrease(int number){
        COMPUTER_NUMBER -= number;
    }

    //获取库存数量
    public int getStockNumber(){
        return COMPUTER_NUMBER;
    }

    //库存压力大了，通知不要采购，销售人员尽快卖电脑
    public void clearStock(){}{
        System.out.println("清理库存数量为： " + COMPUTER_NUMBER);
        super.mediator.execute("stock.clear");
    }
}
