package DesignerPattern.MediatorPattern;

import java.util.Random;

public class Sale extends AbstractColleague {

    public Sale(AbstractMediator mediator) {
        super(mediator);
    }

    //销售IBM电脑
    public void sellIBMComputer(int number){
        super.mediator.execute("sale.sell",number);
        System.out.println("销售IBM电脑"+number+"台");
    }
    //打折处理
    public void offSale(){
        super.mediator.execute("sale.offsell");
    }

    //返回销售情况
    public int getSaleStatus(){
        Random random = new Random(System.currentTimeMillis());
        int saleStatus = random.nextInt(100);
        System.out.println("IBM电脑销售情况为： " + saleStatus);
        return saleStatus;
    }

}
