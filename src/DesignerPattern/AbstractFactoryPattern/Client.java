package DesignerPattern.AbstractFactoryPattern;

/**
 * 前台
 */
public class Client {
    public static void main(String[] args) {
        //客户想要一台D1的宝马,这时前台呼叫D1工厂
        CarWorkShop carWorkShop =  FrontDesk.callCarFactory(CarWorkShopD1.class);
        Car baoMaD1 = carWorkShop.createBaoMa();
        baoMaD1.displacement();
        baoMaD1.run();
        //客户想要D2的奔驰,这时候前台呼叫D2工厂
        carWorkShop = FrontDesk.callCarFactory(CarWorkShopD2.class);
        Car benChiD2 = carWorkShop.createBenChi();
        benChiD2.displacement();
        benChiD2.run();

    }
}
