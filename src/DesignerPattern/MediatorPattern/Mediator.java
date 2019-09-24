package DesignerPattern.MediatorPattern;

public class Mediator extends AbstractMediator {
    @Override
    public void execute(String str, Object... objects) {
        if (str.equals("purchase.buy")){
            //采购电脑

        }
    }

    //采购电脑
    private void buycomputer(int number){
        int saleStatus = super.sale.getSaleStatus();
        if (saleStatus > 80){
            System.out.println("采购IBM电脑： " +number +"台");
            super.stock.increase(number);
        }else {
            int buyNumber = number / 2;
            System.out.println("采购IBM电脑： " + number +"台");
            super.stock.increase(buyNumber);
        }
    }

    //销售电脑
    public void sellComputer(int number){
        if (super.stock.getStockNumber() < number){     //库存不够销售
            super.purchase.buyIBMComputer(number);
        }
        super.stock.decrease(number);
    }

    //打折销售电脑
    public void offSell(){
        System.out.println("打折销售IBM电脑" + stock.getStockNumber() + "台");
    }

    //清仓处理
    private void clearStock(){
        //要求清仓销售
        super.sale.offSale();
        //要求人员不要采购
        super.purchase.refussBuyIBM();
    }

}
