package java.DesignerPattern.AbstractFactoryPattern;

/**
 * 生产排量D2汽车的车间
 */
public class CarWorkShopD2 extends CarWorkShop {
    @Override
    public AbstractBaoMa createBaoMa() {
        return new BaoMaD2();
    }

    @Override
    public AbstractBenChi createBenChi() {
        return new BenChiD2();
    }
}
